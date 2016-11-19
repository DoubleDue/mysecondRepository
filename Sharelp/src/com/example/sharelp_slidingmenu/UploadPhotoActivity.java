package com.example.sharelp_slidingmenu;

import java.io.File;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.example.sharelp.R;
import com.example.sharelp.SharelpApplication;
import com.example.sharelp_utils.UploadUtil;
import com.example.sharelp_utils.Util_Const;

public class UploadPhotoActivity extends Activity implements OnClickListener{
	

    private static final String TAG = "uploadImage";
   // private static String requestURL = "http://192.168.252.1:8080/SharelpServlet/UploadServlet";
    private Button selectImage,uploadImage;
    private ImageView imageView;
    
    private String picPath = null;
    
	
	private SharelpApplication sharelpApplication;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_photochange);
		
		sharelpApplication=(SharelpApplication) getApplication();//�õ�applicationʵ��
		  selectImage = (Button) this.findViewById(R.id.selectImage);
	        uploadImage = (Button) this.findViewById(R.id.uploadImage);
	        selectImage.setOnClickListener(this);
	        uploadImage.setOnClickListener(this);
	        
	        imageView = (ImageView) this.findViewById(R.id.imageView);
	}

	
	 @Override
	    public void onClick(View v) {
	        switch (v.getId()) {
	        case R.id.selectImage:
	            /***
	             * ����ǵ���android���õ�intent��������ͼƬ�ļ�   ��ͬʱҲ���Թ���������  
	             */
	            Intent intent = new Intent();
	            intent.setType("image/*");
	            intent.setAction(Intent.ACTION_GET_CONTENT);
	            startActivityForResult(intent, 1);
	            break;
	        case R.id.uploadImage:
	            File file = new File(picPath);
	            if(file!=null)
	            {
	            	UploadUtil uu=new  UploadUtil( file, Util_Const.UPLOADPHOTO,sharelpApplication.getSno());
	            	uu.start();
	            }
	            Intent intent2=new Intent(UploadPhotoActivity.this, PersonalDataActivity.class);
	            UploadPhotoActivity.this.finish();
	            startActivity(intent2);
	            break;
	        default:
	            break;
	        }
	    }

	    @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        if(resultCode==Activity.RESULT_OK)
	        {
	            /**
	             * ��ѡ���ͼƬ��Ϊ�յĻ����ڻ�ȡ��ͼƬ��;��  
	             */
	            Uri uri = data.getData();
	            Log.e(TAG, "uri = "+ uri);
	            try {
	                String[] pojo = {MediaStore.Images.Media.DATA};
	                
	                Cursor cursor = managedQuery(uri, pojo, null, null,null);
	                if(cursor!=null)
	                {
	                    ContentResolver cr = this.getContentResolver();
	                    int colunm_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
	                    cursor.moveToFirst();
	                    String path = cursor.getString(colunm_index);
	                    /***
	                     * ���������һ���ж���Ҫ��Ϊ�˵����������ѡ�񣬱��磺ʹ�õ��������ļ��������Ļ�����ѡ����ļ��Ͳ�һ����ͼƬ�ˣ������Ļ��������ж��ļ��ĺ�׺��
	                     * �����ͼƬ��ʽ�Ļ�����ô�ſ���   
	                     */
	                    if(path.endsWith("jpg")||path.endsWith("png"))
	                    {
	                        picPath = path;
	                        Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
	                        imageView.setImageBitmap(bitmap);
	                    }else{alert();}
	                }else{alert();}
	                
	            } catch (Exception e) {
	            }
	        }
	        
	        super.onActivityResult(requestCode, resultCode, data);
	    }
	    
	    private void alert()
	    {
	        Dialog dialog = new AlertDialog.Builder(this)
	        .setTitle("��ʾ")
	        .setMessage("��ѡ��Ĳ�����Ч��ͼƬ")
	        .setPositiveButton("ȷ��",
	                new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog,
	                            int which) {
	                        picPath = null;
	                    }
	                })
	        .create();
	        dialog.show();
	    }
	    
	
	
}
