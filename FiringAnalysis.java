package com.app.shikshak;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Intent;
import android.widget.*;

public class FiringAnalysis extends Activity implements OnClickListener {

	private Button indl,overall,bal;
	private ImageButton home;
	
	public void onCreate(Bundle savedInstanceState)
	  {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.firing_analysis);
	
		  home=(ImageButton)findViewById(R.id.home);
		  indl=(Button)findViewById(R.id.IndlFirers);
		  bal=(Button)findViewById(R.id.BalFirers);
		  overall=(Button)findViewById(R.id.Overall);

		  
		  home.setOnClickListener(this);
		  indl.setOnClickListener(this);
		  bal.setOnClickListener(this);
		  overall.setOnClickListener(this);
	  }
	
	public void onClick(View v)
	{
	 Intent i;
		switch(v.getId())
		{
		 case R.id.home:    i= new Intent(this,MainMenu.class);
			                startActivity(i);
			                finish();
		                    break;
		 
		 case R.id.BalFirers://i=new Intent(this,BalFirers.class);
                             //startActivity(i);
			                  break;
		 
		 case R.id.IndlFirers:i=new Intent(this,IndlAnalysis.class);
             					startActivity(i);
             					break;
			 
		 case R.id.Overall:i=new Intent(this,OverallAnalysis.class);
			 				startActivity(i);
			 				break;
		
		}
	}
	
}
