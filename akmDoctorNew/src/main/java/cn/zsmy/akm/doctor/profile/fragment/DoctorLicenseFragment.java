package cn.zsmy.akm.doctor.profile.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import cn.zsmy.akm.doctor.utils.Bimp;
import cn.zsmy.akm.doctor.utils.FileUtils;
import cn.zsmy.akm.doctor.utils.SystemPhoto;
import cn.zsmy.akm.doctor.utils.UrlHelper;
import cn.zsmy.akm.doctor.widget.pop.PhotoPopupWindow;
import cn.zsmy.doctor.R;


/**
 * Created by zzz on 15/12/21.
 */
public class DoctorLicenseFragment extends Fragment implements View.OnClickListener, PhotoPopupWindow.onPopupWindowItemClickListener {
    private View viewById;
    private DoctorInfo.DataEntity dataEntity;
    private boolean WHETHER_FROM_ACTIVITY_BACK = false;
    private ImageView card_1, card_2, doctor_card_1, doctor_card_2;
    private Button card_one_btn, card_two_btn, doctor_card_one_btn, doctor_card_two_btn;
    private TextView card_status_one, card_status_two, card_status_three;
    private PhotoPopupWindow photoPopupWindow;
    private final int CARD_ONE_PIC = 0;
    private final int CARD_TOW_PIC = 1;
    private final int DOCTOR_CARD_ONE_PIC = 2;
    private final int DOCTOR_CARD_TOW_PIC = 3;
    private int PIC = 0;
    private Uri tempUri;
    private List<String> urlDatas;
    private Button next_btn, privous_btn;
    private String picturePath;
    private int PHOTO = 0;
    private int CREAME = 1;
    private int PHOTO_CREAME = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_doctor_license, null);
        viewById = view.findViewById(R.id.profile_center_lv);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        card_1 = (ImageView) view.findViewById(R.id.upload_id_card_positive);
        card_2 = (ImageView) view.findViewById(R.id.upload_id_card_negative);
        doctor_card_1 = (ImageView) view.findViewById(R.id.upload_country_doctorcard);
        doctor_card_2 = (ImageView) view.findViewById(R.id.upload_local_doctorcard);
        card_one_btn = (Button) view.findViewById(R.id.card_submit_one);
        card_two_btn = (Button) view.findViewById(R.id.card_submit_two);
        doctor_card_one_btn = (Button) view.findViewById(R.id.doctor_card_submit_one);
        doctor_card_two_btn = (Button) view.findViewById(R.id.doctor_card_submit_two);
        card_status_one = (TextView) view.findViewById(R.id.card_one_status);
        card_status_two = (TextView) view.findViewById(R.id.card_two_status);
        card_status_three = (TextView) view.findViewById(R.id.card_three_status);
        card_1.setOnClickListener(this);
        card_2.setOnClickListener(this);
        doctor_card_1.setOnClickListener(this);
        doctor_card_2.setOnClickListener(this);
        card_one_btn.setOnClickListener(this);
        card_two_btn.setOnClickListener(this);
        doctor_card_one_btn.setOnClickListener(this);
        doctor_card_two_btn.setOnClickListener(this);
        next_btn = (Button) view.findViewById(R.id.next);
        privous_btn = (Button) view.findViewById(R.id.privous);
        privous_btn.setOnClickListener(this);
        next_btn.setOnClickListener(this);
        photoPopupWindow = new PhotoPopupWindow(getActivity());
        photoPopupWindow.setItemText("拍照", "选择相册");
        photoPopupWindow.setItemClickListener(this);
        urlDatas = new ArrayList<>();
        urlDatas.add("");
        urlDatas.add("");
        urlDatas.add("");
        urlDatas.add("");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ((ProfileInfoActivity) getActivity()).setOnDoctorLicenseListener(new ProfileInfoActivity.onDoctorLicenseListener() {
            @Override
            public void getReturnData(DoctorInfo doctorInfo) {
                if (doctorInfo != null) {
                    dataEntity = doctorInfo.getData();
                    loadViewData();
                }
            }
        });
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

    }

    public void loadViewData() {
        if (dataEntity.getAuthFlag() != null && dataEntity.getAuthFlag().equals("3")) {
            next_btn.setVisibility(View.GONE);
            privous_btn.setVisibility(View.GONE);
            setCardStatus("3");
            setDoctorCardOneStatus("3");
            setDoctorCardTowStatus("3");
            for (int i = 0; i < dataEntity.getDoctorAuth().size(); i++) {
                if (dataEntity.getDoctorAuth().get(i).getZType() != null) {
                    int type = Integer.parseInt((String) dataEntity.getDoctorAuth().get(i).getZType());
                    switch (type) {
                        case 0:
                            ImageLoader.getInstance().displayImage(
                                    UrlHelper.IP + dataEntity.getDoctorAuth().get(i).getAuthPic(), card_1);
                            break;
                        case 1:
                            ImageLoader.getInstance().displayImage(
                                    UrlHelper.IP + dataEntity.getDoctorAuth().get(i).getAuthPic(), card_2);
                            break;
                        case 2:
                            ImageLoader.getInstance().displayImage(
                                    UrlHelper.IP + dataEntity.getDoctorAuth().get(i).getAuthPic(), doctor_card_1);
                            break;
                        case 3:
                            ImageLoader.getInstance().displayImage(
                                    UrlHelper.IP + dataEntity.getDoctorAuth().get(i).getAuthPic(), doctor_card_2);
                            break;
                    }
                }
            }
        } else {


            if (dataEntity.getDoctorAuth() != null) {
                for (int i = 0; i < dataEntity.getDoctorAuth().size(); i++) {
                    if (dataEntity.getDoctorAuth().get(i) != null && dataEntity.getDoctorAuth().get(i).getStatus() != null && dataEntity.getDoctorAuth().get(i).getZType() != null && dataEntity.getDoctorAuth().get(i).getZType().equals("0")) {
                        setCardStatus(dataEntity.getDoctorAuth().get(i).getStatus());
                    } else if (dataEntity.getDoctorAuth().get(i) != null && dataEntity.getDoctorAuth().get(i).getStatus() != null && dataEntity.getDoctorAuth().get(i).getZType() != null && dataEntity.getDoctorAuth().get(i).getZType().equals("1")) {
                        setCardStatus(dataEntity.getDoctorAuth().get(i).getStatus());
                    } else if (dataEntity.getDoctorAuth().get(i) != null && dataEntity.getDoctorAuth().get(i).getStatus() != null && dataEntity.getDoctorAuth().get(i).getZType() != null && dataEntity.getDoctorAuth().get(i).getZType().equals("2")) {
                        setDoctorCardOneStatus(dataEntity.getDoctorAuth().get(i).getStatus());
                    } else if (dataEntity.getDoctorAuth().get(i) != null && dataEntity.getDoctorAuth().get(i).getStatus() != null && dataEntity.getDoctorAuth().get(i).getZType() != null && dataEntity.getDoctorAuth().get(i).getZType().equals("3")) {
                        setDoctorCardTowStatus(dataEntity.getDoctorAuth().get(i).getStatus());
                    }
                    if (dataEntity.getDoctorAuth().get(i).getZType() != null) {
                        int type = Integer.parseInt((String) dataEntity.getDoctorAuth().get(i).getZType());
                        switch (type) {
                            case 0:
                                ImageLoader.getInstance().displayImage(
                                        UrlHelper.IP + dataEntity.getDoctorAuth().get(i).getAuthPic(), card_1);
                                break;
                            case 1:
                                ImageLoader.getInstance().displayImage(
                                        UrlHelper.IP + dataEntity.getDoctorAuth().get(i).getAuthPic(), card_2);
                                break;
                            case 2:
                                ImageLoader.getInstance().displayImage(
                                        UrlHelper.IP + dataEntity.getDoctorAuth().get(i).getAuthPic(), doctor_card_1);
                                break;
                            case 3:
                                ImageLoader.getInstance().displayImage(
                                        UrlHelper.IP + dataEntity.getDoctorAuth().get(i).getAuthPic(), doctor_card_2);
                                break;
                        }
                    }
                }
            }
        }
    }

    private void loadImage(String uri) {
        Request request = new Request(UrlHelper.EDIT_DOCTOR_INFO_IMAGE + "zType=" + String.valueOf(PIC), Request.RequestMethod.POST, getActivity());
        ArrayList<FileEntity> fileEntities = new ArrayList<FileEntity>();
        Bitmap bitmap = null;
        try {
            bitmap = Bimp.revitionImageSize(uri);
            String newStr = uri.substring(uri.lastIndexOf("/") + 1, uri.lastIndexOf("."));
            FileUtils.saveBitmap(bitmap, "" + newStr);
            String path = FileUtils.SDPATH + newStr + ".JPEG";
            fileEntities.add(new FileEntity(new File(path).getName(), "", path));
            request.fileEntities = fileEntities;
            request.put("doctorId", MyApplication.getProfile().getUserId());
            request.setCallback(new FileCallback() {
                @Override
                public void onSuccess(String result) {
                    if (PIC == CARD_ONE_PIC) {
                        setCardStatus("1");
                    } else if (PIC == CARD_TOW_PIC) {
                        setCardStatus("1");
                    } else if (PIC == DOCTOR_CARD_ONE_PIC) {
                        setDoctorCardOneStatus("1");
                    } else if (PIC == DOCTOR_CARD_TOW_PIC) {
                        setDoctorCardTowStatus("1");
                    }
                    Toast.makeText(getActivity(), "提交成功", Toast.LENGTH_SHORT).show();
                }
            });
            RequestManager.getInstance().execute(this.toString(), request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        String type = null;
        switch (v.getId()) {
            case R.id.upload_id_card_positive:
                PIC = CARD_ONE_PIC;
                photoPopupWindow.showPopupWindow();
                break;
            case R.id.upload_id_card_negative:
                PIC = CARD_TOW_PIC;
                photoPopupWindow.showPopupWindow();
                break;
            case R.id.upload_country_doctorcard:
                PIC = DOCTOR_CARD_ONE_PIC;
                photoPopupWindow.showPopupWindow();
                break;
            case R.id.upload_local_doctorcard:
                PIC = DOCTOR_CARD_TOW_PIC;
                photoPopupWindow.showPopupWindow();
                break;
            case R.id.card_submit_one:
                if (dataEntity.getDoctorAuth() != null) {
                    for (int i = 0; i < dataEntity.getDoctorAuth().size(); i++) {
                        if (dataEntity.getDoctorAuth().get(i) != null && dataEntity.getDoctorAuth().get(i).getStatus() != null && dataEntity.getDoctorAuth().get(i).getZType() != null && dataEntity.getDoctorAuth().get(i).getZType().equals("0")) {
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
            case R.id.card_submit_two:
                if (dataEntity.getDoctorAuth() != null) {
                    for (int i = 0; i < dataEntity.getDoctorAuth().size(); i++) {
                        if (dataEntity.getDoctorAuth().get(i) != null && dataEntity.getDoctorAuth().get(i).getStatus() != null && dataEntity.getDoctorAuth().get(i).getZType() != null && dataEntity.getDoctorAuth().get(i).getZType().equals("1")) {
                            type = dataEntity.getDoctorAuth().get(i).getStatus();
                        }
                    }
                }
                if (type == null) {
                    type = "0";
                }
                if (!getClickRemind(1, type)) {

                } else {
                    loadImage(urlDatas.get(1));
                }
                break;
            case R.id.doctor_card_submit_one:
                if (dataEntity.getDoctorAuth() != null) {
                    for (int i = 0; i < dataEntity.getDoctorAuth().size(); i++) {
                        if (dataEntity.getDoctorAuth().get(i) != null && dataEntity.getDoctorAuth().get(i).getStatus() != null && dataEntity.getDoctorAuth().get(i).getZType() != null && dataEntity.getDoctorAuth().get(i).getZType().equals("2")) {
                            type = dataEntity.getDoctorAuth().get(i).getStatus();
                        }
                    }
                }
                if (type == null) {
                    type = "0";
                }
                if (!getClickRemind(2, type)) {

                } else {
                    loadImage(urlDatas.get(2));
                }
                break;
            case R.id.doctor_card_submit_two:
                if (dataEntity.getDoctorAuth() != null) {
                    for (int i = 0; i < dataEntity.getDoctorAuth().size(); i++) {
                        if (dataEntity.getDoctorAuth().get(i) != null && dataEntity.getDoctorAuth().get(i).getStatus() != null && dataEntity.getDoctorAuth().get(i).getZType() != null && dataEntity.getDoctorAuth().get(i).getZType().equals("3")) {
                            type = dataEntity.getDoctorAuth().get(i).getStatus();
                        }
                    }
                }
                if (type == null) {
                    type = "0";
                }
                if (!getClickRemind(3, type)) {

                } else {
                    loadImage(urlDatas.get(3));
                }
                break;
            case R.id.next:
//                loadImage(urlDatas.get(0));
                getActivity().finish();
                //提交
                break;
            case R.id.privous:
                if (getActivity() instanceof Submit) {
                    ((Submit) getActivity()).toUploadPhoto();
                }
                break;

        }

    }

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
        }
        WHETHER_FROM_ACTIVITY_BACK = true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != -1 && null != data) {
            return;
        }
        Uri uri = null;
        if (data != null && data.getData() != null) {
            uri = data.getData();
        }
        switch (requestCode) {
            case CARD_ONE_PIC:
                if (PHOTO_CREAME == CREAME) {
                    if (uri == null) {
                        uri = tempUri;
                        picturePath = SystemPhoto.getFilePathFromUri(getActivity(), uri);
                    }
                } else if (PHOTO_CREAME == PHOTO) {
                    picturePath = StringUtils.getFilePath(getActivity(), data);
                }

                if (picturePath != null) {
                    urlDatas.set(0, picturePath);
                    Bitmap bitmap = ImageUtils.zoomDrawable(ImageUtils.resizePhoto(getActivity(), picturePath), card_1.getWidth(), card_1.getHeight());
                    if (bitmap != null) {
                        card_1.setBackgroundDrawable(ImageUtils.resizePhoto(getActivity(), picturePath));
                        card_1.setImageBitmap(null);
                        photoPopupWindow.dismiss();
                    }
                }
                break;
            case CARD_TOW_PIC:
                if (PHOTO_CREAME == CREAME) {
                    if (uri == null) {
                        uri = tempUri;
                        picturePath = SystemPhoto.getFilePathFromUri(getActivity(), uri);
                    }
                } else if (PHOTO_CREAME == PHOTO) {
                    picturePath = StringUtils.getFilePath(getActivity(), data);
                }

                if (picturePath != null) {
                    urlDatas.set(1, picturePath);
                    Bitmap bitmap = ImageUtils.zoomDrawable(ImageUtils.resizePhoto(getActivity(), picturePath), card_2.getWidth(), card_2.getHeight());
                    if (bitmap != null) {
                        card_2.setBackgroundDrawable(ImageUtils.resizePhoto(getActivity(), picturePath));
                        card_2.setImageBitmap(null);
                        photoPopupWindow.dismiss();
                    }
                }
                break;
            case DOCTOR_CARD_ONE_PIC:
                if (PHOTO_CREAME == CREAME) {
                    if (uri == null) {
                        uri = tempUri;
                        picturePath = SystemPhoto.getFilePathFromUri(getActivity(), uri);
                    }
                } else if (PHOTO_CREAME == PHOTO) {
                    picturePath = StringUtils.getFilePath(getActivity(), data);
                }

                if (picturePath != null) {
                    urlDatas.set(2, picturePath);
                    Bitmap bitmap = ImageUtils.zoomDrawable(ImageUtils.resizePhoto(getActivity(), picturePath), doctor_card_1.getWidth(), doctor_card_1.getHeight());
                    if (bitmap != null) {
                        doctor_card_1.setBackgroundDrawable(ImageUtils.resizePhoto(getActivity(), picturePath));
                        doctor_card_1.setImageBitmap(null);
                        photoPopupWindow.dismiss();
                    }
                }
                break;
            case DOCTOR_CARD_TOW_PIC:
                if (PHOTO_CREAME == CREAME) {
                    if (uri == null) {
                        uri = tempUri;
                        picturePath = SystemPhoto.getFilePathFromUri(getActivity(), uri);
                    }
                } else if (PHOTO_CREAME == PHOTO) {
                    picturePath = StringUtils.getFilePath(getActivity(), data);
                }

                if (picturePath != null) {
                    urlDatas.set(3, picturePath);
                    Bitmap bitmap = ImageUtils.zoomDrawable(ImageUtils.resizePhoto(getActivity(), picturePath), doctor_card_2.getWidth(), doctor_card_2.getHeight());
                    if (bitmap != null) {
                        doctor_card_2.setBackgroundDrawable(ImageUtils.resizePhoto(getActivity(), picturePath));
                        doctor_card_2.setImageBitmap(null);
                        photoPopupWindow.dismiss();
                    }
                }
                break;
            default:
                break;
        }
    }

    public void setCardStatus(String type) {
        int t = Integer.valueOf(type);
        switch (t) {
            case 1:
                card_one_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.upload_doctor_info_pic_up_button_shape));
                card_two_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.upload_doctor_info_pic_up_button_shape));
                card_status_one.setText(null);
                break;
            case 2:
                card_one_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.upload_doctor_info_pic_auth_check_button_shape));
                card_two_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.upload_doctor_info_pic_auth_check_button_shape));
                card_one_btn.setText("已提交");
                card_two_btn.setText("已提交");
                card_one_btn.setClickable(false);
                card_two_btn.setClickable(false);
                card_1.setClickable(false);
                card_2.setClickable(false);
                card_status_one.setText("* 审核中");
                break;
            case 3:
                card_one_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.upload_doctor_info_pic_auth_check_button_shape));
                card_two_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.upload_doctor_info_pic_auth_check_button_shape));
                card_one_btn.setText("已认证");
                card_two_btn.setText("已认证");
                card_one_btn.setClickable(false);
                card_two_btn.setClickable(false);
                card_1.setClickable(false);
                card_2.setClickable(false);
                card_status_one.setText("* 审核通过");
                card_status_one.setTextColor(getResources().getColor(R.color.up_load_pic_doctor_info_check_true));
                break;
            case 4:
                card_one_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.upload_doctor_info_pic_button_shape));
                card_two_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.upload_doctor_info_pic_button_shape));
                card_status_one.setText("* 审核未通过");
                card_status_one.setTextColor(getResources().getColor(R.color.up_load_pic_doctor_info_check_false));
                break;
            default:
                card_one_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.upload_doctor_info_pic_button_shape));
                card_two_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.upload_doctor_info_pic_button_shape));
                card_status_one.setText(null);
                break;
        }
    }

    public void setDoctorCardOneStatus(String type) {
        int t = Integer.valueOf(type);
        switch (t) {
            case 1:
                doctor_card_one_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.upload_doctor_info_pic_up_button_shape));
                card_status_two.setText(null);
                break;
            case 2:
                doctor_card_one_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.upload_doctor_info_pic_auth_check_button_shape));
                doctor_card_one_btn.setText("已提交");
                doctor_card_one_btn.setClickable(false);
                doctor_card_1.setClickable(false);
                card_status_two.setText("* 审核中");
                card_status_two.setTextColor(getResources().getColor(R.color.up_load_pic_doctor_info_check_false));
                break;
            case 3:
                doctor_card_one_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.upload_doctor_info_pic_auth_check_button_shape));
                doctor_card_one_btn.setText("已认证");
                doctor_card_one_btn.setClickable(false);
                doctor_card_1.setClickable(false);
                card_status_two.setText("* 审核通过");
                card_status_two.setTextColor(getResources().getColor(R.color.up_load_pic_doctor_info_check_true));
                break;
            case 4:
                doctor_card_one_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.upload_doctor_info_pic_button_shape));
                card_status_two.setText("* 审核未通过");
                card_status_two.setTextColor(getResources().getColor(R.color.up_load_pic_doctor_info_check_false));
                break;
            default:
                doctor_card_one_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.upload_doctor_info_pic_button_shape));
                card_status_two.setText(null);
                break;
        }
    }

    public void setDoctorCardTowStatus(String type) {
        int t = Integer.valueOf(type);
        switch (t) {
            case 1:
                doctor_card_two_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.upload_doctor_info_pic_up_button_shape));
                card_status_three.setText(null);
                break;
            case 2:
                doctor_card_two_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.upload_doctor_info_pic_auth_check_button_shape));
                doctor_card_two_btn.setClickable(false);
                doctor_card_2.setClickable(false);
                card_status_three.setText("* 审核中");
                break;
            case 3:
                doctor_card_two_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.upload_doctor_info_pic_auth_check_button_shape));
                doctor_card_two_btn.setText("已认证");
                doctor_card_two_btn.setClickable(false);
                doctor_card_2.setClickable(false);
                card_status_three.setText("* 审核通过");
                card_status_three.setTextColor(getResources().getColor(R.color.up_load_pic_doctor_info_check_true));
                break;
            case 4:
                doctor_card_two_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.upload_doctor_info_pic_button_shape));
                card_status_three.setText("* 审核未通过");
                card_status_three.setTextColor(getResources().getColor(R.color.up_load_pic_doctor_info_check_false));
                break;
            default:
                doctor_card_one_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.upload_doctor_info_pic_button_shape));
                card_status_three.setText(null);
                break;
        }
    }

    public boolean getClickRemind(int index, String type) {
        int t = Integer.valueOf(type);
        if (urlDatas.get(index).equals("")) {
            switch (t) {
                case 1:
                    Toast.makeText(getActivity(), "待审核中，请耐心等待", Toast.LENGTH_SHORT).show();
                    break;
                case 4:
                    Toast.makeText(getActivity(), "审核未通过，请更换图片", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(getActivity(), "请上传图片", Toast.LENGTH_SHORT).show();
                    break;
            }
            return false;
        }
        return true;

    }

    public interface Submit {
        void toUploadPhoto();
    }
}

