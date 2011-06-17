package com.app.shikshak;


import org.achartengine.chartdemo.demo.chart.SalesBarChart;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.database.*;
import android.graphics.Color;
import android.widget.TableRow.LayoutParams;

public class OverallAnalysis extends Activity implements OnClickListener{

	private DataBaseServices DB ;
	private Button okButton,helpButton,backButton,chartButton;
	private Spinner subunitspinner, typespinner, weaponspinner, yearspinner, percentspinner, visspinner, stdspinner;
	private ArrayAdapter<CharSequence> adapter1 = null, adapter2 = null, adapter3 = null, adapter4=null, adapter5=null, adapter6=null, adapter7=null;

    private TableRow specs;
    private Cursor c;
    private TableLayout t1;
    private TableRow tr;
    
    private boolean flag;
    
	private TextView tv[][];
	private ImageButton info[];
    
	private String args []=null;
	private String percent;
	private String date[];
    
	 public void onCreate(Bundle savedInstanceState)
	  {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.analysis_overall);
		  
		  flag=false;
		  t1 = (TableLayout) findViewById(R.id.overallanalysis_tablelayout);
		  okButton = (Button)findViewById(R.id.ok);
		  helpButton = (Button)findViewById(R.id.help);
		  backButton = (Button)findViewById(R.id.back);
		  chartButton=(Button)findViewById(R.id.chart);
		  
		  specs=(TableRow)findViewById(R.id.tablespecs);
		  
          okButton.setOnClickListener( this);
		  backButton.setOnClickListener( this);
		  helpButton.setOnClickListener( this);
		  chartButton.setOnClickListener(this);
		  
		  subunitspinner=(Spinner)findViewById(R.id.subunit_spinner);
		  adapter1 = ArrayAdapter.createFromResource(this,R.array.subunit_select,android.R.layout.simple_spinner_item);
		  adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		  subunitspinner.setAdapter(adapter1);
		  
		  weaponspinner=(Spinner)findViewById(R.id.wpn_spinner);
		  adapter2 = ArrayAdapter.createFromResource(this,R.array.weapon_select,android.R.layout.simple_spinner_item);
		  adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		  weaponspinner.setAdapter(adapter2);
		  
		  typespinner=(Spinner)findViewById(R.id.type_spinner);
		  adapter3 = ArrayAdapter.createFromResource(this,R.array.typeOfFiring_select,android.R.layout.simple_spinner_item);
		  adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		  typespinner.setAdapter(adapter3);
		  
		  percentspinner=(Spinner)findViewById(R.id.percent_spinner);
		  adapter4 = ArrayAdapter.createFromResource(this,R.array.percentage_array,android.R.layout.simple_spinner_item);
		  adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		  percentspinner.setAdapter(adapter4);
		  
		  stdspinner=(Spinner)findViewById(R.id.std_spinner);
		  adapter5 = ArrayAdapter.createFromResource(this,R.array.std_array,android.R.layout.simple_spinner_item);
		  adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		  stdspinner.setAdapter(adapter5);
		  
		  visspinner=(Spinner)findViewById(R.id.vis_spinner);
		  adapter6 = ArrayAdapter.createFromResource(this,R.array.vis_select,android.R.layout.simple_spinner_item);
		  adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		  visspinner.setAdapter(adapter6);
		  
		  yearspinner=(Spinner)findViewById(R.id.year_spinner);
		  adapter7 = ArrayAdapter.createFromResource(this,R.array.year_select,android.R.layout.simple_spinner_item);
		  adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		  yearspinner.setAdapter(adapter7);
		  
		  DB=new DataBaseServices(this);
		  
	  }
	 
	 
	 //must be edited
	 public void onClick(View v)throws SQLException, NullPointerException
		{
		 
		 int q;
		 Intent i;
		 int k=0;
		 double month[]=new double[12];
		 for(int j=0;j<12;j++)
			 month[j]=0.0;
		 
		 float p,pt,tot,per=0;
		 String d;
		 
		 for(int j=0;j<6;j++)
		 {
			 args[j]=new String();
			 args[j]=null;
		 }
		 
			switch(v.getId())
			{
			case R.id.ok:       args[0]=(String) adapter1.getItem(subunitspinner.getSelectedItemPosition());
								args[0]=args[0].trim();
				    
			    				args[1]=(String) adapter2.getItem(weaponspinner.getSelectedItemPosition());
			    				args[1]=args[1].trim();
				    
			    				args[2]=(String) adapter3.getItem(typespinner.getSelectedItemPosition());
				    			args[2]=args[2].trim();
				
			    				percent=(String) adapter4.getItem(percentspinner.getSelectedItemPosition());
			    				percent=percent.trim();
			    				if(percent!="ALL")
			    					per=Float.parseFloat(percent);
			    				else
			    					per=0;
			    						
			    				args[3]=(String) adapter5.getItem(stdspinner.getSelectedItemPosition());
			    				args[3]=args[3].trim();
			    				
			    				args[4]=(String) adapter6.getItem(visspinner.getSelectedItemPosition());
			    				args[4]=args[4].trim();
			    				
			    				args[5]=(String) adapter7.getItem(yearspinner.getSelectedItemPosition());
			    				args[5]=args[5].trim();
			    				
			    				
			  					 
			  					 if(args[0]!=null && args[1]!=null && args[2]!=null && args[3]!=null && args[4]!=null && args[5]!=null && percent!=null)
			  					 {
			  						 
			  						 
			  						 specs.setVisibility(0);
			  						 
			  						 c=DB.overallFirers(args);
			  						 
			  						 if(c.moveToFirst())   
			  						 {	 
			  							flag=true;
			  						  do
			  						  {  
			  							pt=Float.valueOf(c.getString(7).trim()).floatValue();
			  				            tot=(Float.valueOf(c.getString(8).trim()).floatValue())*3;
			  				            p=(pt/tot)*100;
			  				            
			  				            
			  				            if((per==0)||(p<=per && p>=(per-10) ))
			  				            {  
			  							  
			  							tr = new TableRow(this); 
			  						   tr.setLayoutParams(new LayoutParams(
			  				                    LayoutParams.FILL_PARENT,
			  				                    LayoutParams.WRAP_CONTENT));   
			  						   	
			  						//num
			  				            tv[k][0] = new TextView(this);
			  				            tv[k][0].setId(k*20);
			  				            tv[k][0].setText(c.getString(0));
			  				            tv[k][0].setTextColor(Color.WHITE);
			  				            tv[k][0].setLayoutParams(new LayoutParams(
			  				                    LayoutParams.FILL_PARENT,
			  				                    LayoutParams.WRAP_CONTENT));
			  				            tr.addView(tv[k][0]);
			  				            
			  				            //rank
			  				            tv[k][1] = new TextView(this);
			  				            tv[k][1].setId(k*20+1);
			  				            tv[k][1].setText(c.getString(1));
			  				            tv[k][1].setTextColor(Color.WHITE);
			  				            tv[k][1].setLayoutParams(new LayoutParams(
			  				                    LayoutParams.FILL_PARENT,
			  				                    LayoutParams.WRAP_CONTENT));
			  				            tr.addView(tv[k][1]);
			  				            
			  				            //name
			  				            tv[k][2] = new TextView(this);
			  				            tv[k][2].setId(k*20+2);
			  				            tv[k][2].setText(c.getString(2));
			  				            tv[k][2].setTextColor(Color.WHITE);
			  				            tv[k][2].setLayoutParams(new LayoutParams(
			  				                    LayoutParams.FILL_PARENT,
			  				                    LayoutParams.WRAP_CONTENT));
			  				            tr.addView(tv[k][2]);
			  					 
			  				            //subunit
			  				            tv[k][3] = new TextView(this);
			  				            tv[k][3].setId(k*20+3);
			  				            tv[k][3].setText(c.getString(3));
			  				            tv[k][3].setTextColor(Color.WHITE);
			  				            tv[k][3].setLayoutParams(new LayoutParams(
			  				                    LayoutParams.FILL_PARENT,
			  				                    LayoutParams.WRAP_CONTENT));
			  				            tr.addView(tv[k][3]);
			  				            
			  				            //wpn
			  				            tv[k][4]= new TextView(this);
			  				            tv[k][4].setId(k*20+4);
			  				            tv[k][4].setText(c.getString(4));
			  				            tv[k][4].setTextColor(Color.WHITE);
			  				            tv[k][4].setLayoutParams(new LayoutParams(
			  				                    LayoutParams.FILL_PARENT,
			  				                    LayoutParams.WRAP_CONTENT));
			  				            tr.addView(tv[k][4]);
			  					 
			  				            //butt
			  				            tv[k][5] = new TextView(this);
			  				            tv[k][5].setId(k*20+5);
			  				            tv[k][5].setText(c.getString(5));
			  				            tv[k][5].setTextColor(Color.WHITE);
			  				            tv[k][5].setLayoutParams(new LayoutParams(
			  				                    LayoutParams.FILL_PARENT,
			  				                    LayoutParams.WRAP_CONTENT));
			  				            tr.addView(tv[k][5]);
			  				            
			  				            //reg
			  				            tv[k][6] = new TextView(this);
			  				            tv[k][6].setId(k*20+6);
			  				            tv[k][6].setText(c.getString(6));
			  				            tv[k][6].setTextColor(Color.WHITE);
			  				            tv[k][6].setLayoutParams(new LayoutParams(
			  				                    LayoutParams.FILL_PARENT,
			  				                    LayoutParams.WRAP_CONTENT));
			  				            tr.addView(tv[k][6]);
			  				           
			  				            //pts
			  				            tv[k][7] = new TextView(this);
			  				            tv[k][7].setId(k*20+7);
			  				            tv[k][7].setText(c.getString(7));
			  				            tv[k][7].setTextColor(Color.WHITE);
			  				            tv[k][7].setLayoutParams(new LayoutParams(
			  				                    LayoutParams.FILL_PARENT,
			  				                    LayoutParams.WRAP_CONTENT));
			  				            tr.addView(tv[k][7]);
			  				            
			  				            //%		different rounds for different people how to calculate percent ?
			  				            tv[k][8] = new TextView(this);
			  				            tv[k][8].setId(k*20+8);  
			  				            
			  				            
			  				            
			  				            d=c.getString(10);
			  				            String a[]=d.split("-");
			  				            q=Integer.parseInt(a[1]);
			  				            month[q-1]+=p;
			  				            
			  				            tv[k][8].setText(String.valueOf(p));
			  				            tv[k][8].setTextColor(Color.WHITE);
			  				            tv[k][8].setLayoutParams(new LayoutParams(
			  				                    LayoutParams.FILL_PARENT,
			  				                    LayoutParams.WRAP_CONTENT));
			  				            tr.addView(tv[k][8]); 
			  				            
			  				            //stds
			  				            tv[k][9] = new TextView(this);
			  				            tv[k][9].setId(k*20+9);
			  				            tv[k][9].setText(c.getString(9));
			  				            tv[k][9].setTextColor(Color.WHITE);
			  				            tv[k][9].setLayoutParams(new LayoutParams(
			  				                    LayoutParams.FILL_PARENT,
			  				                    LayoutParams.WRAP_CONTENT));
			  				            tr.addView(tv[k][9]);  
			  				           
			  				            date[k]=new String(c.getString(10));
			  				           
			  				            
			  				            //info
			  				            info[k] = new ImageButton(this);
			  				            info[k].setId(k*20+10);
			  				            info[k].setOnClickListener(this);
			  				            //set image
			  				            info[k].setLayoutParams(new LayoutParams(
			  				                    LayoutParams.FILL_PARENT,
			  				                    LayoutParams.WRAP_CONTENT));
			  				            tr.addView(info[k]);  
			  						   
			  				            
			  				          t1.addView(tr, new TableLayout.LayoutParams(
			  			                    LayoutParams.FILL_PARENT,
			  			                    LayoutParams.WRAP_CONTENT));
			  				          
			  				          k++;
			  				            }
			  						  }while(c.moveToNext());
			  						 }
			  						 else
			  						 {
			  							Toast.makeText(getBaseContext(), "No Entries in Database matching the given fields.", Toast.LENGTH_LONG).show();
			  						 }	 
			  						 DB.close();
			  						 
			  					  }
			  					  else
			  					  {
			  						Toast.makeText(getBaseContext(), "Error in Other Fields.", Toast.LENGTH_LONG).show();
			  					    break;
			  					  }
			  					 
			  					 
			  					 
			  					break; 
		
			 case R.id.chart:if(flag==false)
				 					Toast.makeText(getBaseContext(), "Error. Please enter the fields required.", Toast.LENGTH_LONG).show();
			 					else
			 					{
			 					 SalesBarChart s = new SalesBarChart();
			 					 i=s.getPercent(month,this);	
			 					 startActivity(i);
			 					 
			 					}
								break;
			 
			 case R.id.back:	i=new Intent(this,FiringAnalysis.class);
								startActivity(i);
								finish();
			                    break;
			 
			 case R.id.help:
				 
				                  break;
				                  
			 default:	if(flag==true)
			 			{	
			 		 
			 		//history or info button
			 		 for(int j=0;j<k;j++)
			 		 {
			 		 	if(info[j].getId()==v.getId())
			 		 	{
			 		 		/*i=new Intent(this,History.class);
			 		 		i.putExtra("com.app.shikshak.history_no", tv[j][0].getText());
		                    i.putExtra("com.app.shikshak.history_dof",date[j]);  
		                    i.putExtra("com.app.shikshak.history_regno",tv[j][6].getText());
			 		 		startActivity(i); */
			 		 		break;
			 		 	}	
			 		 }
			 		 
			 		 
			 		 
			 		}
			}
		}
	 
	 
	 
}