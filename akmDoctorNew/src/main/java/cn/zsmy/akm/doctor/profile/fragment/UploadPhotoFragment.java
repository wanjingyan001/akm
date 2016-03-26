package cn.zsmy.akm.doctor.profile.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.zsmy.akm.doctor.bean.DoctorInfo;
import cn.zsmy.akm.doctor.chat.utils.ImageUtils;
import cn.zsmy.akm.doctor.chat.utils.StringUtils;
import cn.zsmy.akm.doctor.home.MyApplication;
import cn.zsmy.akm.doctor.http.FileCallback;
import cn.zsmy.akm.doctor.http.FileEntity;
import cn.zsmy.akm.doctor.http.Request;
import cn.zsmy.akm.doctor.http.RequestManager;
import cn.zsmy.akm.doctor.profile.ProfileInfoActivity;
import cn.zsmy.akm.doctor.profile.edit.PreviewActivity;
import cn.zsmy.akm.doctor.profile.edit.VideoRecordActivity;
import cn.zsmy.akm.doctor.utils.Bimp;
import cn.zsmy.akm.doctor.utils.Constants;
import cn.zsmy.akm.doctor.utils.DateUtils;
import cn.zsmy.akm.doctor.utils.FileUtils;
import cn.zsmy.akm.doctor.utils.SystemPhoto;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.akm.doctor.widget.pop.PhotoPopupWindow;
import cn.zsmy.doctor.R;


/**
 * Created by zzz on 15/12/21.
 */
public class UploadPhotoFragment extends Fragment implements View.OnClickListener, PhotoPopupWindow.onPopupWindowItemClickListener {
    private View viewById;
    private ImageView loadImg, loadVideo;
    private Uri tempUri;
    private DoctorInfo.DataEntity dataEntity;
    private boolean WHETHER_FROM_ACTIVITY_BACK = false;
    private String ztype;
    private Button next_btn, privous_btn, photo_btn, video_btn;
    private final int DOCTOR_CREAM_IMAGE_PIC = 3;
    private final int DOCTOR_IMAGE_PIC = 4;
    private final int DOCTOR_VIDEO = 5;
    private int PIC = 0;
    private List<String> urlDatas;
    private PhotoPopupWindow photoPopupWindow;
    private String picturePath;
    private TextView card_status, cream_status;
    private int PHOTO = 0;
    private int CREAME = 1;
    private int PHOTO_CREAME = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_profile_upload_photo, null);
        viewById = view.findViewById(R.id.profile_center_lv);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.privous).setOnClickListener(this);
        view.findViewById(R.id.next).setOnClickListener(this);
        next_btn = (Button) view.findViewById(R.id.next);
        privous_btn = (Button) view.findViewById(R.id.privous);
        photo_btn = (Button) view.findViewById(R.id.image_submit_one);
        video_btn = (Button) view.findViewById(R.id.vedio_submit_two);
        card_status = (TextView) view.findViewById(R.id.profile_up_card_pic_status);
        cream_status = (TextView) view.findViewById(R.id.profile_up_cream_status);
        privous_btn.setOnClickListener(this);
        next_btn.setOnClickListener(this);
        photo_btn.setOnClickListener(this);
        video_btn.setOnClickListener(this);
        loadImg = ((ImageView) view.findViewById(R.id.load_your_image));
        loadVideo = ((ImageView) view.findViewById(R.id.load_your_video));
        loadImg.setOnClickListener(this);
        loadVideo.setOnClickListener(this);
        urlDatas = new ArrayList<>();
        urlDatas.add("");
        urlDatas.add("");
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((ProfileInfoActivity) getActivity()).setUpLoadPhotoListener(new ProfileInfoActivity.onUpLoadPhotoListener() {
            @Override
            public void getReturnData(DoctorInfo doctorInfo) {
                if (doctorInfo != null) {
                    dataEntity = doctorInfo.getData();
                    loadViewData();
                }
            }
        });
    }

    //    (0:资料未上传, 1:待审核, 2:审核中,3:审核通过, 4:审核未通过)
    public void loadViewData() {
        if (dataEntity.getAuthFlag() != null && dataEntity.getAuthFlag().equals("3")) {
            next_btn.setVisibility(View.GONE);
            privous_btn.setVisibility(View.GONE);
            setCardStatus("3");
            setCreamStatus("3");
            if (dataEntity.getDoctorAuth() != null) {
                for (int i = 0; i < dataEntity.getDoctorAuth().size(); i++) {
                    if (dataEntity.getDoctorAuth().get(i) != null && dataEntity.getDoctorAuth().get(i).getStatus() != null && dataEntity.getDoctorAuth().get(i).getZType() != null && dataEntity.getDoctorAuth().get(i).getZType().equals("4")) {
                        ImageLoader.getInstance().displayImage(
                                UrlHelper.IP + dataEntity.getDoctorAuth().get(i).getAuthPic(), loadImg);
                    } else if (dataEntity.getDoctorAuth().get(i) != null && dataEntity.getDoctorAuth().get(i).getStatus() != null && dataEntity.getDoctorAuth().get(i).getZType() != null && dataEntity.getDoctorAuth().get(i).getZType().equals("6")) {
                        ImageLoader.getInstance().displayImage(
                                UrlHelper.IP + dataEntity.getDoctorAuth().get(i).getAuthPic(), loadVideo);
                    }
                }

            }
        } else if (dataEntity.getAuthFlag() != null && dataEntity.getAuthFlag().equals("2")) {
            setCardStatus("2");
            setCreamStatus("2");
            if (dataEntity.getDoctorAuth() != null) {
                for (int i = 0; i < dataEntity.getDoctorAuth().size(); i++) {
                    if (dataEntity.getDoctorAuth().get(i) != null && dataEntity.getDoctorAuth().get(i).getStatus() != null && dataEntity.getDoctorAuth().get(i).getZType() != null && dataEntity.getDoctorAuth().get(i).getZType().equals("4")) {
                        ImageLoader.getInstance().displayImage(
                                UrlHelper.IP + dataEntity.getDoctorAuth().get(i).getAuthPic(), loadImg);
                    } else if (dataEntity.getDoctorAuth().get(i) != null && dataEntity.getDoctorAuth().get(i).getStatus() != null && dataEntity.getDoctorAuth().get(i).getZType() != null && dataEntity.getDoctorAuth().get(i).getZType().equals("6")) {
                        ImageLoader.getInstance().displayImage(
                                UrlHelper.IP + dataEntity.getDoctorAuth().get(i).getAuthPic(), loadVideo);
                    }
                }
            }
        } else {
            if (dataEntity.getDoctorAuth() != null) {
                for (int i = 0; i < dataEntity.getDoctorAuth().size(); i++) {
                    if (dataEntity.getDoctorAuth().get(i) != null && dataEntity.getDoctorAuth().get(i).getStatus() != null && dataEntity.getDoctorAuth().get(i).getZType() != null && dataEntity.getDoctorAuth().get(i).getZType().equals("4")) {
                        setCardStatus(dataEntity.getDoctorAuth().get(i).getStatus());
                    } else if (dataEntity.getDoctorAuth().get(i) != null && dataEntity.getDoctorAuth().get(i).getStatus() != null && dataEntity.getDoctorAuth().get(i).getZType() != null && dataEntity.getDoctorAuth().get(i).getZType().equals("5")) {
                        setCreamStatus(dataEntity.getDoctorAuth().get(i).getStatus());
                    }
                    if (dataEntity.getDoctorAuth().get(i) != null && dataEntity.getDoctorAuth().get(i).getStatus() != null && dataEntity.getDoctorAuth().get(i).getZType() != null && dataEntity.getDoctorAuth().get(i).getZType().equals("4")) {
                        ImageLoader.getInstance().displayImage(
                                UrlHelper.IP + dataEntity.getDoctorAuth().get(i).getAuthPic(), loadImg);
                        System.out.println("图片URL=" + UrlHelper.IP + dataEntity.getDoctorAuth().get(i).getAuthPic());
                    }
                    if (dataEntity.getDoctorAuth().get(i) != null && dataEntity.getDoctorAuth().get(i).getStatus() != null && dataEntity.getDoctorAuth().get(i).getZType() != null && dataEntity.getDoctorAuth().get(i).getZType().equals("5")) {
                    }
                    if (dataEntity.getDoctorAuth().get(i) != null && dataEntity.getDoctorAuth().get(i).getStatus() != null && dataEntity.getDoctorAuth().get(i).getZType() != null && dataEntity.getDoctorAuth().get(i).getZType().equals("6")) {
                        ImageLoader.getInstance().displayImage(
                                UrlHelper.IP + dataEntity.getDoctorAuth().get(i).getAuthPic(), loadVideo);
                    }
                }
            }
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (!WHETHER_FROM_ACTIVITY_BACK) {
            if (dataEntity != null) {
            }
        } else {
            WHETHER_FROM_ACTIVITY_BACK = false;
        }
//        if (RecordingActivity.uri != null) {
//            urlDatas.set(1, RecordingActivity.uri);
//            Log.i("TAG", RecordingActivity.uri);
//            if (RecordingActivity.uri != null) {
//                Bitmap bitmap = Bimp.getVideoThumbnail(RecordingActivity.uri);
////                Drawable drawable = new BitmapDrawable(bitmap);
////                loadVideo.setImageBitmap(null);
////                loadVideo.setBackgroundDrawable(null);
////                loadVideo.setBackgroundDrawable(drawable);
////                bitmap = null;
//                loadVideo.setImageResource(R.mipmap.ic_load_video);
//            }
//        }
        if (VideoRecordActivity.uri != null) {
            urlDatas.set(1, VideoRecordActivity.uri);
            loadVideo.setImageBitmap(VideoRecordActivity.getBitmap(VideoRecordActivity.uri));
            Log.i("TAG", VideoRecordActivity.uri);
            Bitmap bitmap = VideoRecordActivity.getBitmap(VideoRecordActivity.uri);
            try {
                VideoRecordActivity.saveBitMap(bitmap, String.valueOf(DateUtils.getCurrentTime()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            UploadImage(VideoRecordActivity.mImgUri);
//            if (RecordingActivity.uri != null) {
//                Bitmap bitmap = Bimp.getVideoThumbnail(VideoRecordActivity.uri);
//                Drawable drawable = new BitmapDrawable(bitmap);
//                loadVideo.setImageBitmap(null);
//                loadVideo.setBackgroundDrawable(null);
//                loadVideo.setBackgroundDrawable(drawable);
//                bitmap = null;
//                loadVideo.setImageResource(R.mipmap.ic_load_video);
//
//            }

        }

    }

    @Override
    public void onClick(View v) {
        String type = null;
        switch (v.getId()) {
            case R.id.privous:
                if (getActivity() instanceof ToLicense) {
                    ((ToLicense) getActivity()).toIntroduction();
                }
                break;
            case R.id.next:
                if (getActivity() instanceof ToLicense) {
                    ((ToLicense) getActivity()).toLicense();
                }
                break;
            case R.id.image_submit_one:
                if (dataEntity.getDoctorAuth() != null) {
                    for (int i = 0; i < dataEntity.getDoctorAuth().size(); i++) {
                        if (dataEntity.getDoctorAuth().get(i) != null && dataEntity.getDoctorAuth().get(i).getStatus() != null && dataEntity.getDoctorAuth().get(i).getZType() != null && dataEntity.getDoctorAuth().get(i).getZType().equals("4")) {
                            type = dataEntity.getDoctorAuth().get(i).getStatus();
                        }
                    }
                }
                if (type == null) {
                    type = "0";
                }
                if (!getClickRemind(0, type)) {

                } else {
                    loadImage(urlDatas.get(0));
                }
                break;
            case R.id.vedio_submit_two:
                if (dataEntity.getDoctorAuth() != null) {
                    for (int i = 0; i < dataEntity.getDoctorAuth().size(); i++) {
                        if (dataEntity.getDoctorAuth().get(i) != null && dataEntity.getDoctorAuth().get(i).getStatus() != null && dataEntity.getDoctorAuth().get(i).getZType() != null && dataEntity.getDoctorAuth().get(i).getZType().equals("5")) {
                            type = dataEntity.getDoctorAuth().get(i).getStatus();
                        }
                    }
                }
                if (type == null) {
                    type = "0";
                }
                if (!getClickRemind(1, type)) {

                } else {
                    loadVideo(urlDatas.get(1));
                }
                break;
            case R.id.load_your_image:
                PIC = DOCTOR_IMAGE_PIC;
                photoPopupWindow = new PhotoPopupWindow(getActivity());
                photoPopupWindow.setItemText("拍照", "选择相册");
                photoPopupWindow.setItemClickListener(this);
                photoPopupWindow.showPopupWindow();
                break;
            case R.id.load_your_video:
                PIC = DOCTOR_VIDEO;
                if (VideoRecordActivity.uri == null) {
                    try {
//                    Intent intent = new Intent(getActivity(), RecordingActivity.class);
                        Intent intent = new Intent(getActivity(), VideoRecordActivity.class);
                        intent.putExtra("CONTEXT", Constants.UPLOAD_RECORD_VALUES);
                        startActivityForResult(intent, PIC);
                        WHETHER_FROM_ACTIVITY_BACK = true;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    //查看录制的视频
//                    Intent intent = new Intent(Intent.ACTION_VIEW);
//                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intent.setDataAndType(Uri.fromFile(new File(VideoRecordActivity.uri)), "video/*");
//                    startActivity(intent);
                    Intent intent = new Intent(getActivity(), PreviewActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("video", VideoRecordActivity.uri);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                break;
        }
    }

    private void loadImage(String uri) {
        Request request = new Request(UrlHelper.EDIT_DOCTOR_INFO_IMAGE + "zType=" + String.valueOf(PIC), Request.RequestMethod.POST, getActivity());
        System.out.println("URL:" + UrlHelper.EDIT_DOCTOR_INFO_IMAGE + "zType=" + String.valueOf(PIC));
        ArrayList<FileEntity> fileEntities = new ArrayList<FileEntity>();
        Bitmap bitmap = null;
        try {
            bitmap = Bimp.revitionImageSize(uri);
            System.out.println("Uri：" + uri);
            String newStr = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf("."));
            FileUtils.saveBitmap(bitmap, "" + newStr);
            String path = FileUtils.SDPATH + newStr + ".JPEG";
            System.out.println("NewStr：" + newStr);
            System.out.println("Path：" + path);
            fileEntities.add(new FileEntity(new File(path).getName(), "", path));
            request.fileEntities = fileEntities;
            request.put("doctorId", MyApplication.getProfile().getUserId());
//        request.put("zType", String.valueOf(PIC));
            request.setCallback(new FileCallback() {
                @Override
                public void onSuccess(String result) {
                    if (PIC == DOCTOR_IMAGE_PIC) {
                        setCardStatus("1");
                    } else {
                        setCreamStatus("1");
                    }
                    Toast.makeText(getActivity(), "提交成功", Toast.LENGTH_SHORT).show();
                }
            });
            RequestManager.getInstance().execute(this.toString(), request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 上传提交视频
     *
     * @param uri
     */
    private void loadVideo(String uri) {
        Request request = new Request(UrlHelper.EDIT_DOCTOR_INFO_IMAGE + "zType=" + String.valueOf(PIC), Request.RequestMethod.POST, getActivity());
        ArrayList<FileEntity> fileEntities = new ArrayList<FileEntity>();
        /**
         * 修改人 yutaotao
         * 修改时间：2016/3/8
         * 修改内容：上传的视频文件不能使用bitmap来处理，bitmap时处理图片的
         * 使用bitmap处理会导致找不到新生成的文件
         */
//        Bitmap bitmap = null;
//        try {
//            bitmap = Bimp.revitionImageSize(uri);
//            System.out.println("Uri："+uri);
//            String newStr = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf("."));
//            FileUtils.saveBitmap(bitmap, "" + newStr);
//            String path = FileUtils.SDPATH + newStr + ".mp4";
//            System.out.println("NewStr："+newStr);
//            System.out.println("PIC:"+PIC);
//            System.out.println("Path："+path);
        fileEntities.add(new FileEntity(new File(uri).getName(), "", uri));
        request.fileEntities = fileEntities;
        request.put("doctorId", MyApplication.getProfile().getUserId());
//        request.put("zType", String.valueOf(PIC));
        request.setCallback(new FileCallback() {
            @Override
            public void onSuccess(String result) {
                if (PIC == DOCTOR_IMAGE_PIC) {
                    setCardStatus("1");
                } else {
                    setCreamStatus("1");
                }
                Toast.makeText(getActivity(), "提交成功", Toast.LENGTH_SHORT).show();
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 上传视频缩略图
     *
     * @param uri
     */
    private void UploadImage(String uri) {
        System.out.println("URI:" + uri);
        Request request = new Request(UrlHelper.EDIT_DOCTOR_INFO_IMAGE + "zType=" + String.valueOf(6), Request.RequestMethod.POST, getActivity());
        System.out.println("URL:" + UrlHelper.EDIT_DOCTOR_INFO_IMAGE + "zType=" + String.valueOf(6));
        ArrayList<FileEntity> fileEntities = new ArrayList<FileEntity>();
        fileEntities.add(new FileEntity(new File(uri).getName(), "", uri));
        request.fileEntities = fileEntities;
        request.put("doctorId", MyApplication.getProfile().getUserId());
//        request.put("zType", String.valueOf(PIC));
        request.setCallback(new FileCallback() {
            @Override
            public void onSuccess(String result) {
                if (PIC == DOCTOR_IMAGE_PIC) {
                    setCardStatus("1");
                } else {
                    setCreamStatus("1");
                }
                Toast.makeText(getActivity(), "视频缩略图提交成功", Toast.LENGTH_SHORT).show();
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);

    }

    /**
     * 实现poppupwindow接口，通过position判断当前button
     *
     * @param0 拍照
     * @param1 选择相册
     ****/
    @Override
    public void onItemClick(int position) {
        switch (position) {
            case 0:
                PHOTO_CREAME = CREAME;
                Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = new File(Environment.getExternalStorageDirectory()
                        .getAbsolutePath()
                        + "/"
                        + String.valueOf(System.currentTimeMillis()) + ".jpg");
                tempUri = Uri.fromFile(file);
                openCameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, tempUri);
                startActivityForResult(openCameraIntent, PIC);
                break;
            case 1:
                PHOTO_CREAME = PHOTO;
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, PIC);
                break;
            default:
                break;
        }

        WHETHER_FROM_ACTIVITY_BACK = true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != -1) {
            return;
        }
        Uri uri = null;
        if (data != null && data.getData() != null) {
            uri = data.getData();
        }
        switch (requestCode) {
            case DOCTOR_IMAGE_PIC:
                if (PHOTO_CREAME == CREAME) {
                    if (uri == null) {
                        uri = tempUri;
                        picturePath = SystemPhoto.getFilePathFromUri(getActivity(), uri);
                    }
                } else if (PHOTO_CREAME == PHOTO) {
                    picturePath = StringUtils.getFilePath(getActivity(), data);
                }
                break;
            case DOCTOR_VIDEO:
//                    urlDatas.set(1, picturePath);
//                      Log.i("TAG",picturePath);
//                    if(picturePath!=null){
//                        loadImg.setImageBitmap(BitmapFactory.decodeFile(picturePath));
//                    }
                break;
            default:
                break;
        }
        if (picturePath != null) {
            Log.i("TAG", picturePath);
            urlDatas.set(0, picturePath);
            Bitmap bitmap = ImageUtils.zoomDrawable(ImageUtils.resizePhoto(getActivity(), picturePath), loadImg.getWidth(), loadImg.getHeight());
            if (bitmap != null) {
                loadImg.setBackgroundDrawable(ImageUtils.resizePhoto(getActivity(), picturePath));
                loadImg.setImageBitmap(null);
//                loadImg.setImageBitmap(bitmap);
                photoPopupWindow.dismiss();
            }
        }

    }

    public interface ToLicense {
        void toIntroduction();

        void toLicense();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ImageUtils.releaseImageResouce(loadImg);
        ImageUtils.releaseImageResouce(loadVideo);
    }

    //    (0:资料未上传, 1:待审核, 2:审核中,3:审核通过, 4:审核未通过)
    public void setCardStatus(String type) {
        int t = Integer.valueOf(type);
        switch (t) {
            case 1:
                photo_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.upload_doctor_info_pic_up_button_shape));
                card_status.setText(null);
                break;
            case 2:
                photo_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.upload_doctor_info_pic_auth_check_button_shape));
                photo_btn.setText("已提交");
                photo_btn.setClickable(false);
                card_status.setText("* 审核中");
                loadImg.setClickable(false);
                break;
            case 3:
                photo_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.upload_doctor_info_pic_auth_check_button_shape));
                photo_btn.setText("已认证");
                photo_btn.setClickable(false);
                loadImg.setClickable(false);
                card_status.setText("* 审核通过");
                card_status.setTextColor(getResources().getColor(R.color.up_load_pic_doctor_info_check_true));
                break;
            case 4:
                photo_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.upload_doctor_info_pic_button_shape));
                card_status.setText("* 审核未通过");
                card_status.setTextColor(getResources().getColor(R.color.up_load_pic_doctor_info_check_false));
                break;
            default:
                photo_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.upload_doctor_info_pic_button_shape));
                cream_status.setText(null);
                break;
        }

    }

    public void setCreamStatus(String type) {
        int t = Integer.valueOf(type);
        switch (t) {
            case 1:
                video_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.upload_doctor_info_pic_up_button_shape));
                cream_status.setText(null);
                for (int i = 0; i < dataEntity.getDoctorAuth().size(); i++) {
                    if (dataEntity.getDoctorAuth().get(i).getZType().equals("6")) {
                        ImageLoader.getInstance().displayImage(
                                UrlHelper.IP + dataEntity.getDoctorAuth().get(i).getAuthPic(), loadVideo);
                    }
                }
                break;
            case 2:
                video_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.upload_doctor_info_pic_auth_check_button_shape));
                video_btn.setClickable(false);
                loadVideo.setClickable(false);
                cream_status.setText("* 审核中");
                for (int i = 0; i < dataEntity.getDoctorAuth().size(); i++) {
                    if (dataEntity.getDoctorAuth().get(i).getZType().equals("6")) {
                        ImageLoader.getInstance().displayImage(
                                UrlHelper.IP + dataEntity.getDoctorAuth().get(i).getAuthPic(), loadVideo);
                    }
                }
                break;
            case 3:
                video_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.upload_doctor_info_pic_auth_check_button_shape));
                video_btn.setText("已认证");
                video_btn.setClickable(false);
                loadVideo.setClickable(false);
                cream_status.setText("* 审核通过");
                cream_status.setTextColor(getResources().getColor(R.color.up_load_pic_doctor_info_check_true));
//                loadVideo.setImageResource(R.mipmap.ic_load_video);
//                loadVideo.setImageBitmap(VideoRecordActivity.getBitmap(VideoRecordActivity.uri));
                for (int i = 0; i < dataEntity.getDoctorAuth().size(); i++) {
                    if (dataEntity.getDoctorAuth().get(i).getZType().equals("6")) {
                        ImageLoader.getInstance().displayImage(
                                UrlHelper.IP + dataEntity.getDoctorAuth().get(i).getAuthPic(), loadVideo);
                    }
                }
                break;
            case 4:
                video_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.upload_doctor_info_pic_button_shape));
                cream_status.setText("* 审核未通过");
                cream_status.setTextColor(getResources().getColor(R.color.up_load_pic_doctor_info_check_false));
                break;
            default:
                video_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.upload_doctor_info_pic_button_shape));
                cream_status.setText(null);
                break;
        }
    }

    public boolean getClickRemind(int index, String type) {
        int t = Integer.valueOf(type);
        if (urlDatas.get(index).equals("")) {
            switch (t) {
                case 1:
                    Toast.makeText(getActivity(), "待审核中，重新加入图片", Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    Toast.makeText(getActivity(), "审核未通过，重新加入图片", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(getActivity(), "还没加入图片", Toast.LENGTH_SHORT).show();
                    break;
            }
            return false;
        }
        return true;

    }

}
