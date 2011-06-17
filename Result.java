package com.app.shikshak;
 

import java.util.Calendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.AbsoluteLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
 
public class Result extends Activity implements OnClickListener {
    
	Panel targetSurface;
	Button clear,tap,res_H,res_prev,res_next,res_save,res_rpt_fir,res_rpt_det,res_nxt_det;
	ImageButton res_back;
	EditText res_armyno,res_vis,res_tgttype,res_buttno,res_regno,res_name,res_rank,res_rg,
	res_rds,res_pos,res_sunit,res_type,res_wpn,b3pt_h,b3pt_p,b2pt_h,b2pt_p,b1pt_h,b1pt_p,wo_h,wo_p
	,misfire,gpg,tot_h,tot_p,misfire_p;
	TextView res_tgt_no,res_det_no;
	CheckBox limberUpFault,sightingFault,nilFault,triggerFault,breathingFault,flinchingFault,buckingFault,faultyWpnFault;
    DataBaseServices objDB;
    Cursor cursor,cursor1,mCur;
    int firerNo,detailNo;
    ImageView up,down;
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setContentView(R.layout.result);
        //setContentView(R.layout.result);
      LinearLayout Lin= (LinearLayout)this.findViewById(R.id.Lin);
       
      targetSurface =new Panel(this);
        Lin.addView(targetSurface);
        
        clear= (Button)findViewById(R.id.clearButton);
        
        clear.setOnClickListener(this);

        tap= (Button)findViewById(R.id.tapButton);
        
        tap.setOnClickListener(this);
        targetSurface.setTap(false);
        firerNo = this.getIntent().getIntExtra("FirerNo",0);
		detailNo = this.getIntent().getIntExtra("DetailNo",1);
        SharedPreferences p = this.getSharedPreferences("StorePrefs", MODE_WORLD_READABLE);
        String tgtType=p.getString("TgtTypeDet"+detailNo, "");
        if (tgtType.equals("30 cms Gp tgt")|| tgtType.equals("120 cms zeroing tgt"))
        {
        	targetSurface.setImage(R.drawable.zeroing);
        }
        else if(tgtType.equals("Fig 11"))
        	targetSurface.setImage(R.drawable.fig11);
        else if ( tgtType.equals("Two Fig 11"))
        		targetSurface.setImage(R.drawable.f11_2);
        else
        	targetSurface.setImage(R.drawable.fig12);
        
          
        		res_H = (Button) findViewById(R.id.res_H);   
        		res_prev = (Button) findViewById(R.id    .res_prev);
        		res_next = (Button) findViewById(R.id.res_next);
        		res_save = (Button) findViewById(R.id.res_save);    
        		res_rpt_fir = (Button) findViewById(R.id.res_rpt_fir);   
        		 res_rpt_det = (Button) findViewById(R.id.res_rpt_det);
        		res_nxt_det = (Button) findViewById(R.id.res_nxt_det);    
        		res_back = (ImageButton) findViewById(R.id.res_back);    



        		up= (ImageView)findViewById(R.id.up);
        		up.setOnClickListener(this);
        		down= (ImageView)findViewById(R.id.down);
        		down.setOnClickListener(this);

        		
        		res_H.setOnClickListener(this);
        		res_prev.setOnClickListener(this);
        		res_next.setOnClickListener(this);
        		res_save.setOnClickListener(this);
        		res_rpt_fir.setOnClickListener(this);
        		res_rpt_det.setOnClickListener(this);
        		res_nxt_det.setOnClickListener(this);
        		res_back.setOnClickListener(this);
        		
        		
        		
        		
        		res_armyno = (EditText) findViewById(R.id.res_armyno);
        		res_vis = (EditText) findViewById(R.id.res_vis);
        		res_tgttype = (EditText) findViewById(R.id.res_tgttype);
        		res_buttno = (EditText) findViewById(R.id.res_buttno);
        		res_regno = (EditText) findViewById(R.id.res_regno);
        		res_name = (EditText) findViewById(R.id.res_name);
        		res_rank = (EditText) findViewById(R.id.res_rank);
        		res_rg = (EditText) findViewById(R.id.res_rg);
        		res_rds = (EditText) findViewById(R.id.res_rds);
        		res_pos = (EditText) findViewById(R.id.res_pos);
        		res_sunit = (EditText) findViewById(R.id.res_subunit);
        		res_type = (EditText) findViewById(R.id.res_type);
        		res_wpn = (EditText) findViewById(R.id.res_wpn);
        		b3pt_h = (EditText) findViewById(R.id.b3pt_h);
        		b3pt_p = (EditText) findViewById(R.id.b3pt_p);
        		b2pt_h = (EditText) findViewById(R.id.b2pt_h);
        		b2pt_p = (EditText) findViewById(R.id.b2pt_p);
        		b1pt_h = (EditText) findViewById(R.id.b1pt_h);
        		b1pt_p = (EditText) findViewById(R.id.b1pt_p);
        		wo_h = (EditText) findViewById(R.id.wo_h);
        		wo_p = (EditText) findViewById(R.id.wo_p);
        		misfire = (EditText) findViewById(R.id.misfire);
        		misfire_p = (EditText) findViewById(R.id.misfire_p);
        		gpg = (EditText) findViewById(R.id.gpg);
        		tot_h = (EditText) findViewById(R.id.tot_h);
        		tot_p = (EditText) findViewById(R.id.tot_p);
        
        		res_tgt_no = (TextView) findViewById(R.id.res_tgt_no);
        		res_det_no = (TextView) findViewById(R.id.res_det_no);
        
        
        		
        		limberUpFault = (CheckBox) findViewById(R.id.limberUpFault);
        		sightingFault = (CheckBox) findViewById(R.id.sightingFault);
        		triggerFault = (CheckBox) findViewById(R.id.triggerFault);
        		breathingFault = (CheckBox) findViewById(R.id.breathingFault);
        		flinchingFault = (CheckBox) findViewById(R.id.flinchingFault);
        		buckingFault = (CheckBox) findViewById(R.id.buckingFault);
        		faultyWpnFault = (CheckBox) findViewById(R.id.faultyWpnFault);
        		nilFault = (CheckBox)findViewById(R.id.nilFault);



        		
        		
        		objDB=new DataBaseServices(this);
        		
        		
        		cursor = objDB.getAllFromWeaponForFirer(detailNo);
        		cursor.moveToFirst();
        		if(cursor.getCount()==0 || cursor.getCount()<firerNo){
        			Toast.makeText(this,"No user found",Toast.LENGTH_LONG).show();
        			return;
        		}
        		cursor.moveToPosition(firerNo);
        		res_tgt_no.setText(Integer.toString(firerNo+1));
        		res_det_no.setText(Integer.toString(detailNo));
        		res_armyno.setText(cursor.getString(0));
        		res_wpn.setText(cursor.getString(1));
        		res_buttno.setText(cursor.getString(2));
        		res_regno.setText(cursor.getString(3));
        		cursor1=objDB.getNameAndSunitAndRank(cursor.getString(0));
        		cursor1.moveToFirst();
        		res_name.setText(cursor1.getString(0));
        		res_rank.setText(cursor1.getString(1));
        		res_sunit.setText(cursor1.getString(2));
        	
        		
        		   res_vis.setText(p.getString("Vis", ""));
        		   res_type.setText( p.getString("Type", ""));
        		   res_rg.setText(p.getInt("Range", 5)+"M");
        		   res_pos.setText( p.getString("Posn", ""));
        		   res_tgttype.setText(p.getString("TgtType", ""));
        		   misfire.setText("0");
        		   
        		   mCur=cursor;
        		   cursor1.close();
        		   
        		   final Calendar cal = Calendar.getInstance();
        		   cursor=objDB.getDetailedResult(res_armyno.getText().toString().trim(),
        					cal.get(Calendar.YEAR)+"-"+cal.get(Calendar.MONTH)+"-"+cal.get(Calendar.DAY_OF_MONTH),
        					res_wpn.getText().toString().trim(),
        					res_regno.getText().toString().trim(),
        				res_buttno.getText().toString().trim());
        		 
        		   cursor.moveToFirst();
        		   if(cursor.getCount()<=0){
        			   Toast.makeText(this, "This entry isn't saved to databases. Please go to the check target page to save this result bfore anlysing it.", Toast.LENGTH_LONG).show();
        		  
        		   }
        		   else
        		   {
        		   misfire.setText(cursor.getString(0));
        		   tot_p.setText(cursor.getString(1));
        		   gpg.setText(cursor.getString(2));
        		   //std.setText(cursor.getString(3));
        		   tot_h.setText(cursor.getString(4));
        			res_rds.setText(cursor.getString(4));
        		
        		   Coordinates [] coor = objDB.getPoints(res_armyno.getText().toString().trim(),
       					cal.get(Calendar.YEAR)+"-"+cal.get(Calendar.MONTH)+"-"+cal.get(Calendar.DAY_OF_MONTH),
    					res_wpn.getText().toString().trim(),
    					res_regno.getText().toString().trim(),
    				res_buttno.getText().toString().trim());
        		   targetSurface.setPoints(coor);
        		   
        		   boolean flag=false;
        		   if(p.getInt("Range", 5)==300) flag =true;
        		   Hits H=targetSurface.setTap(flag);
       			b3pt_h.setText(Integer.toString(H.getHit3()));
       			b3pt_p.setText(Integer.toString(3*H.getHit3()));
       			b2pt_h.setText(Integer.toString(H.getHit2()));
       			b2pt_p.setText(Integer.toString(2*H.getHit2()));
       			b1pt_h.setText(Integer.toString(H.getHit1()));
       			b1pt_p.setText(Integer.toString(1*H.getHit1()));
        		   
        		wo_h.setText(
        				Integer.toString((Integer.parseInt(tot_h.getText().toString())-
        				(
        						Integer.parseInt( b3pt_h.getText().toString())+
        						Integer.parseInt( b2pt_h.getText().toString())+
        						Integer.parseInt( b1pt_h.getText().toString())+
        						Integer.parseInt( misfire.getText().toString())
				
        				)))
    		
        		);
        		
        		misfire_p.setText(Integer.toString(Integer.parseInt(misfire.getText().toString())*3));
        		wo_p.setText(Integer.toString(Integer.parseInt(wo_h.getText().toString())*1));
        		
        		tot_p.setText(Integer.toString(
        				Integer.parseInt(b3pt_p.getText().toString())+
        				Integer.parseInt(b2pt_p.getText().toString())+
        				Integer.parseInt(b1pt_p.getText().toString())+
        				Integer.parseInt(wo_p.getText().toString())+
        				Integer.parseInt(misfire_p.getText().toString())));
        		   }
        		   
    }

	

	
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId())
		{
		case R.id.res_back:
			 Intent ba=new Intent(this,TargetCheck.class);
			 ba.putExtra("FirerNo",firerNo);
			 ba.putExtra("DetailNo",detailNo);
			this.startActivity(ba);
			finish();
			 
			 break;
		
		
		case R.id.res_next:
			
			if(mCur.moveToNext())
			{
				 Intent c=new Intent(this,Result.class);
				 c.putExtra("FirerNo",firerNo+1);
				 c.putExtra("DetailNo",detailNo);
				this.startActivity(c);
				finish();
				 
				 break;
			}
			Toast.makeText(this, "No Next available", Toast.LENGTH_LONG).show();
			break;
			
		case R.id.res_prev:
			if(mCur.moveToPrevious() && firerNo!=0)
			{
				 Intent c=new Intent(this,Result.class);
				 c.putExtra("FirerNo",firerNo-1);
				 c.putExtra("DetailNo",detailNo);
				this.startActivity(c);
				finish();
				 
				 break;
			}
			Toast.makeText(this, "No Previous available", Toast.LENGTH_LONG).show();
			break;
		
			
		case R.id.res_save:
			int fault=0;
			
			if(limberUpFault.isChecked()) fault |=0x1;
    		if(sightingFault.isChecked()) fault |=0x2;
    		if(triggerFault.isChecked()) fault |= 0x4;
    		if(breathingFault.isChecked()) fault |= 0x8;
    		if(flinchingFault.isChecked()) fault |= 0x10;
    		if(buckingFault.isChecked()) fault |= 0x20;
    		if(faultyWpnFault.isChecked()) fault |= 0x40;
    		if(nilFault.isChecked()) fault |= 0x80;
    		
    		 final Calendar cal = Calendar.getInstance();
    		
    		int ret =objDB.updateFault(res_armyno.getText().toString().trim(),
			cal.get(Calendar.YEAR)+"-"+cal.get(Calendar.MONTH)+"-"+cal.get(Calendar.DAY_OF_MONTH),
			res_wpn.getText().toString().trim(),
			res_regno.getText().toString().trim(),
			res_buttno.getText().toString().trim(),fault);
    		if(ret==1){
    			Toast.makeText(this, "Successfully loged the faults.", Toast.LENGTH_LONG).show();
    		}
    		break;
			
		}
	}
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
	        Intent intent = new Intent(this,TargetCheck.class);
	        this.startActivity(intent);
	        finish();
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}
}
 