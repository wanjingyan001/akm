package cn.zsmy.akm.salon.activity.choosePhoto;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.wei.library.widget.ProgressDialogUtils;
import cn.zsmy.akm.BaseTitleActivity;
import cn.zsmy.akm.R;
import cn.zsmy.akm.home.MyApplication;
import cn.zsmy.akm.http.FileCallback;
import cn.zsmy.akm.http.FileEntity;
import cn.zsmy.akm.http.JsonParser;
import cn.zsmy.akm.http.Request;
import cn.zsmy.akm.http.RequestManager;
import cn.zsmy.akm.http.StringCallback;
import cn.zsmy.akm.salon.activity.MaxLengthWatcher;
import cn.zsmy.akm.salon.bean.POST;
import cn.zsmy.akm.utils.ImageUtils;
import cn.zsmy.akm.utils.SystemPhoto;
import cn.zsmy.akm.utils.UrlHelpper;


/**
 * 发新帖
 */
public class ForumReleaseActivity extends BaseTitleActivity implements AdapterView.OnItemClickListener {
    private int COMMENT_POST = 0;//提交帖子
    private GridView noScrollgridview;
    private GridAdapter adapter;
    private View parentView;
    private PopupWindow pop = null;
    private LinearLayout ll_popup;
    public static Bitmap bimap;
    private EditText mPostContentEdt;
    private EditText mPostTitleEdt;
    private String platform;
    private static final String TAG = "MyApplication";
    private String picturePath;
    private Uri tempUri;
    public static final int GET_PHOTO_ALBUM = 50;

    /**
     * 1. 设置布局
     */
    @Override
    protected void setContentView() {
        Res.init(this);
//        bimap = BitmapFactory.decodeResource(
//                getResources(),
//                R.drawable.ic_add_photo);
        PublicWay.activityList.add(this);
        parentView = getLayoutInflater().inflate(R.layout.activity_selectimg, null);
        setContentView(parentView);
        MyApplication.getInstance().addActivity(this);
        Init();
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        mPostContentEdt = ((EditText) findViewById(R.id.post_content_edt));
        mPostTitleEdt = ((EditText) findViewById(R.id.post_title_edt));
        mPostTitleEdt.addTextChangedListener(new MaxLengthWatcher(20, mPostTitleEdt, this));
        mPostContentEdt.addTextChangedListener(new MaxLengthWatcher(300, mPostContentEdt, this));
    }

    /**
     * 3. 初始化ui数据
     */
    @Override
    protected void initializeData() {
        setTitle(getText(R.string.posted));
        Intent intent = getIntent();
        platform = intent.getStringExtra("platform");
        noScrollgridview.setOnItemClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("platform", platform);
    }

    public static Intent getIntent(Context context) {
        return new Intent(context, ForumReleaseActivity.class);
    }


    public void Init() {
        pop = new PopupWindow(ForumReleaseActivity.this);
        View view = getLayoutInflater().inflate(R.layout.item_popupwindows, null);
        ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);
        pop.setWidth(LayoutParams.MATCH_PARENT);
        pop.setHeight(LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(new BitmapDrawable());
        pop.setFocusable(true);
        pop.setOutsideTouchable(true);
        pop.setContentView(view);
        RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
        Button bt1 = (Button) view
                .findViewById(R.id.item_popupwindows_camera);
        Button bt2 = (Button) view
                .findViewById(R.id.item_popupwindows_Photo);
        Button bt3 = (Button) view
                .findViewById(R.id.item_popupwindows_cancel);
        parent.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                photo();
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ForumReleaseActivity.this,
                        AlbumActivity.class);
                startActivityForResult(intent, GET_PHOTO_ALBUM);
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });
        bt3.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                pop.dismiss();
                ll_popup.clearAnimation();
            }
        });

        noScrollgridview = (GridView) findViewById(R.id.noScrollgridview);
        noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
        adapter = new GridAdapter(this);
        adapter.update();
        noScrollgridview.setAdapter(adapter);


    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("TAG", "checked:" + position + ">>>>>>>" + Bimp.tempSelectBitmap.size());
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
        pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
    }

    @SuppressLint("HandlerLeak")
    public class GridAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private int selectedPosition = -1;
        private boolean shape;

        public boolean isShape() {
            return shape;
        }

        public void setShape(boolean shape) {
            this.shape = shape;
        }

        public GridAdapter(Context context) {
            inflater = LayoutInflater.from(context);
        }

        public void update() {
            loading();
        }

        public int getCount() {
            if (Bimp.tempSelectBitmap.size() == 9) {
                return 9;
            }
            return (Bimp.tempSelectBitmap.size() + 1);
        }

        public ImageItem getItem(int position) {
            return Bimp.tempSelectBitmap.get(position);
        }

        public long getItemId(int position) {
            return position;
        }

        public void setSelectedPosition(int position) {
            selectedPosition = position;
        }

        public int getSelectedPosition() {
            return selectedPosition;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.item_published_grida,
                        parent, false);
                holder = new ViewHolder();
                holder.image = (ImageView) convertView.findViewById(R.id.item_grida_image);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            Log.d(TAG, "position:" + position + ",size:" + Bimp.tempSelectBitmap.size());
            if (position == Bimp.tempSelectBitmap.size()) {
                holder.image.setImageBitmap(BitmapFactory.decodeResource(
                        getResources(), R.drawable.ic_add_photo));
                if (position == 9) {
                    holder.image.setVisibility(View.GONE);
                }
            } else {
                Bitmap bitmap = Bimp.tempSelectBitmap.get(position).getBitmap();
                if (bitmap != null) {
                    holder.image.setImageBitmap(bitmap);
                } else {
                    holder.image.setImageBitmap(
                            ImageUtils.loadBitmap(Bimp.tempSelectBitmap.get(position).getImagePath(),
                                    holder.image.getWidth(),
                                    holder.image.getHeight()));
                }
            }
            return convertView;
        }

        public class ViewHolder {
            public ImageView image;
        }

        Handler handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 1:
                        adapter.notifyDataSetChanged();
                        break;
                }
                super.handleMessage(msg);
            }
        };

        public void loading() {
            new Thread(new Runnable() {
                public void run() {
                    while (true) {
                        if (Bimp.max == Bimp.tempSelectBitmap.size()) {
                            Message message = new Message();
                            message.what = 1;
                            handler.sendMessage(message);
                            break;
                        } else {
                            try {
                                ImageItem imageItem = Bimp.tempSelectBitmap.get(Bimp.max);
                                String path = imageItem.getImagePath();
                                Bitmap bm = Bimp.revitionImageSize(path);
                                imageItem.setBitmap(bm);
                                String newStr = path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf("."));
//                                String newStr = String.valueOf(System.currentTimeMillis());
                                FileUtils.saveBitmap(bm, "" + newStr);
                                Bimp.max += 1;
                                Message message = new Message();
                                message.what = 1;
                                handler.sendMessage(message);
                                if (Bimp.max > PublicWay.num) {
                                    break;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }).start();
        }
    }

    public String getString(String s) {
        String path = null;
        if (s == null)
            return "";
        for (int i = s.length() - 1; i > 0; i++) {
            s.charAt(i);
        }
        return path;
    }

    protected void onRestart() {
        adapter.update();
        super.onRestart();
    }

    private static final int TAKE_PICTURE = 0x000001;

    public void photo() {
        Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(Environment.getExternalStorageDirectory()
                .getAbsolutePath()
                + "/"
                + String.valueOf(System.currentTimeMillis()) + ".jpg");
        tempUri = Uri.fromFile(file);
        openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
        startActivityForResult(openCameraIntent, SystemPhoto.CAMERA_IMAGE_CODE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri uri = null;
        if (data != null && data.getData() != null) {
            uri = data.getData();
        }
        switch (requestCode) {
            case TAKE_PICTURE:
                if (Bimp.tempSelectBitmap.size() < 9) {
                    ImageItem takePhoto = new ImageItem();
                    if (uri == null) {
                        uri = tempUri;
                    }
                    String str = SystemPhoto.getFilePathFromUri(this, uri);
                    takePhoto.setImagePath(str);
                    Bimp.tempSelectBitmap.add(takePhoto);
//                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
//                    Cursor cursor = getContentResolver().query(selectedImage,
//                            filePathColumn, null, null, null);
//                    cursor.moveToFirst();
//                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//                    picturePath = cursor.getString(columnIndex);
//                    cursor.close();
//                    takePhoto.setImagePath(picturePath);

                }
                break;
            case GET_PHOTO_ALBUM:

                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_reply_post, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String title = mPostTitleEdt.getText().toString();
        String content = mPostContentEdt.getText().toString();
        int itemId = item.getItemId();
        if (itemId == R.id.reply_post) {
            if (!TextUtils.isEmpty(title) && !TextUtils.isEmpty(content)) {
                submitPost(title, content);
            } else {
                Toast.makeText(this, "请填写内容", Toast.LENGTH_SHORT).show();
            }
        } else if (itemId == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void submitPost(String title, String content) {
        Request request = new Request(UrlHelpper.COMMINT_POST, Request.RequestMethod.POST, this);
        ProgressDialogUtils.showProgressDialog(this, "提交中");
        request.put("title", title);
        request.put("content", content);
        request.put("forumPlateId", platform);
        request.setCallback(new StringCallback() {
            @Override
            public void onSuccess(String result) {
                POST post = JsonParser.deserializeFromJson(result, POST.class);
                String id = post.getData().getId();
                if (Bimp.tempSelectBitmap.size() != 0) {
                    submitPic(id, 0);
                } else {
                    ProgressDialogUtils.closeProgressDialog();
                    finish();
                }
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }


    private void submitPic(final String postId, final int i) {
        Request request = new Request(UrlHelpper.ADD_POST_PIC + "id=" + postId, Request.RequestMethod.POST, this);
        List<String> cacheImages = getCacheImages();
        String uri = cacheImages.get(i);
        try {
            long fileSize = Bimp.getFileSize(new File(uri));
            Log.d("TAG", postId + "========" + uri + ",大小为：" + Bimp.bytes2kb((int) fileSize));
        } catch (Exception e) {
            e.printStackTrace();
        }
        ArrayList<FileEntity> fileEntities = new ArrayList<FileEntity>();
        fileEntities.add(new FileEntity(new File(uri).getName(), "", uri));
        request.fileEntities = fileEntities;
        request.put("id", postId);
        request.setCallback(new FileCallback() {
            @Override
            public void onSuccess(String result) {
                if (i < Bimp.tempSelectBitmap.size() - 1) {
                    submitPic(postId, i + 1);
                } else {
                    ProgressDialogUtils.closeProgressDialog();
                    finish();
                }
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }

    public ArrayList<String> getCacheImages() {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < Bimp.tempSelectBitmap.size(); i++) {
            String str = Bimp.tempSelectBitmap.get(i).getImagePath();
            String Str = str.substring(str.lastIndexOf("/") + 1, str.lastIndexOf("."));
            list.add(FileUtils.SDPATH + Str + ".JPEG");
        }
        return list;
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

    @SuppressLint("NewApi")
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
        if (Bimp.tempSelectBitmap.size() == 0) {
        } else {
            Bimp.tempSelectBitmap.clear();
            Bimp.max = 0;
        }
    }
}

