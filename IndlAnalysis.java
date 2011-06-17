package com.app.shikshak;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.database.*;
import android.graphics.Color;
import android.widget.TableRow.LayoutParams;
import android.view.View.OnFocusChangeListener;
import org.achartengine.example.LineChart;

public class IndlAnalysis extends Activity implements OnClickListener,OnFocusChangeListener{

	private DataBaseServices DB ;
	private Button okButton,helpButton,backButton,chartButton;
	private Spinner typespinner, weaponspinner, yearspinner;
	private ArrayAdapter<CharSequence> adapter1 = null,adapter2 = null,adapter3 = null;

	private EditText numtext,ranktext,nametext,subunittext;
    private TableRow specs;
    private Cursor c;
    private TableLayout t1;
    private TableRow tr;
    
    private int FAULT_DIALOG=1001,FAULT;
    private boolean flag;
    private String attributes[] ={"ARMY_NO","RANK","NAME","SUBUNIT"};
	/*private String parameters[]={
			"DOF",
			"WPN",
			"BUTT_NO",
			"REG_NO",
			"TYPE",
			"POS",
			"RANGE",
			"TOTPTS",
			"ROUNDS",
			"STD",
			"FAULTS"};*/
	
    private int faults[];
    private String faultstring[]={
    						"FAULTY LIMBER UP",
    						"SIGHTING",
    						"TRIGGER OPERATION",
    						"BREATHING",
    						"FLINCHING",
    						"BUCKING",
    						"FAULTY WEAPON",
    						"NIL"
    						};
    
	private TextView tv[][];
	private Button faultbutton[];
	private ImageButton info[];
    
	private String args []=null;
    private String numarg []=null;
	
	//need to edit
	 public void onCreate(Bundle savedInstanceState)
	  {
		  super.onCreate(savedInstanceState);
		  setContentView(R.layout.analysis_indl);
		  
		  //implement focus change for number textbox
		  
		  
		  flag=false;
		  t1 = (TableLayout) findViewById(R.id.indlanalysis_tablelayout);
		  okButton = (Button)findViewById(R.id.ok);
		  helpButton = (Button)findViewById(R.id.help);
		  backButton = (Button)findViewById(R.id.back);
		  chartButton=(Button)findViewById(R.id.chart);
		  
		  
		  numtext=(EditText)findViewById(R.id.notextbox);
		  ranktext=(EditText)findViewById(R.id.ranktextbox);
		  nametext=(EditText)findViewById(R.id.nametextbox);
		  subunittext=(EditText)findViewById(R.id.subunittextbox);

		  specs=(TableRow)findViewById(R.id.tablespecs);
		  
		  numtext.setOnFocusChangeListener(this);
          okButton.setOnClickListener( this);
		  backButton.setOnClickListener( this);
		  helpButton.setOnClickListener( this);
		  chartButton.setOnClickListener(this);
		  
		  
		  typespinner=(Spinner)findViewById(R.id.typespinner);
		  adapter1 = ArrayAdapter.createFromResource(this,R.array.typeOfFiring_select,android.R.layout.simple_spinner_item);
		  adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		  typespinner.setAdapter(adapter1);
		  
		  weaponspinner=(Spinner)findViewById(R.id.weaponspinner);
		  adapter2 = ArrayAdapter.createFromResource(this,R.array.weapon_select,android.R.layout.simple_spinner_item);
		  adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		  weaponspinner.setAdapter(adapter2);
		  
		  yearspinner=(Spinner)findViewById(R.id.yearspinner);
		  adapter3 = ArrayAdapter.createFromResource(this,R.array.year_array,android.R.layout.simple_spinner_item);
		  adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		  yearspinner.setAdapter(adapter3);
		  
		  
		  DB=new DataBaseServices(this);
		  
		  
	  }
	 
	 
	 
	 //to display the faults
	 public Dialog onCreateDialog(int id)
	 {
		 AlertDialog.Builder builder = new AlertDialog.Builder(this);
	     
		 String s;
		 
		 if(FAULT==0)
		 {
			 s="No Faults";
		 }	 
		 else
		 {
			 s="Faults are";
			 
			 if((FAULT & 1)==1)
			 {
				 s.concat("\n" + faultstring[0]);
			 }	 
			 if((FAULT & 2)==2)
			 {
				 s.concat("\n" + faultstring[1]);
			 }	 
			 if((FAULT & 4)==4)
			 {
				 s.concat("\n" + faultstring[2]);
			 }	 
			 if((FAULT & 8)==8)
			 {
				 s.concat("\n" + faultstring[3]);
			 }	 
			 if((FAULT & 16)==16)
			 {
				 s.concat("\n" + faultstring[4]);
			 }	 
			 if((FAULT & 32)==32)
			 {
				 s.concat("\n" + faultstring[5]);
			 }	 
			 if((FAULT & 64)==64)
			 {
				 s.concat("\n" + faultstring[6]);
			 }	 
			 if((FAULT & 128)==128)
			 {
				 s.concat("\n" + faultstring[7]);
			 }	 
		 }	 
			 
		 builder.setMessage(s).setCancelable(false)
	                       .setNeutralButton("OK", new DialogInterface.OnClickListener() {
	                                                  public void onClick(DialogInterface dialog, int id) {dialog.cancel();} } );
	     AlertDialog alert = builder.create();
	     return alert;
	 }
	
	 
	 
	 //to auto display rank name subunit etc.
	 public void onFocusChange(View v,boolean b)
	  {
		 
		 switch(v.getId())
		 {
		 	case R.id.notextbox: 
		 		
		 	if(!b)
		    {
			  
			  SpannableStringBuilder ssb=(SpannableStringBuilder)numtext.getText();
			  String s=ssb.toString();
			  s=s.trim();
			  numarg=new String[]{s};
			  
			  if(!s.matches("((IC|SS|RC|SL|SC)[0-9]{5}[A-Z]{1})|(JC[0-9]{5}[A-Z]{1})|([0-9]{8}[A-Z]{1})"))
			  {
				  Toast.makeText(getBaseContext(), "Error in No.", Toast.LENGTH_LONG).show();
				  break;  
			  }
			  else
			  {
				 c=DB.askquery(DB.getDB_TABLE_DETAILS(),attributes,"ARMY_NO= ?",numarg,null,null,null);
				 if(!c.moveToFirst())
					{
						Toast.makeText(getBaseContext(), "Entered Army No doesn't exist in the database.", Toast.LENGTH_LONG).show();
						break;
					}
			     
				 ranktext.setText(c.getString(1));
		         ranktext.setTextColor(Color.BLACK);
		         
		         nametext.setText(c.getString(2));
		         nametext.setTextColor(Color.BLACK);
		         
		         subunittext.setText(c.getString(3));
		         subunittext.setTextColor(Color.BLACK);
		         
		         DB.close();
			  }
		    }
			break;
		 }
	  }
	 
	 
	 
	 public void onClick(View v)throws SQLException,NullPointerException
		{
		 float p,pt,tot;
		 args=new String[4];
		 Intent i;
		 int k=0;
		 args[0]=args[1]=args[2]=args[3]=null;
		 
			switch(v.getId())
			{
			case R.id.ok:       args[0]=numtext.getText().toString();
			  					args[0]=args[0].trim();
			  					 
			  					args[1]=(String) adapter1.getItem(typespinner.getSelectedItemPosition());
			  				    args[1]=args[1].trim();
			  				    
			  				    args[2]=(String) adapter2.getItem(weaponspinner.getSelectedItemPosition());
			  				    args[2]=args[2].trim();
			  				    
			  				    args[3]=(String) adapter3.getItem(yearspinner.getSelectedItemPosition());
			  				    args[3]=args[3].trim();
			  					 
			  					 if(args[0].matches("((IC|SS|RC|SL|SC)[0-9]{5}[A-Z]{1})|(JC[0-9]{5}[A-Z]{1})|([0-9]{8}[A-Z]{1})"))
			  					 {
			  						 
			  					  if(args[1]!=null && args[2]!=null && args[3]!=null)
			  					  {  
			  						 
			  						 specs.setVisibility(0);
			  						 
			  						 //c=DB.askquery(DB.getDB_TABLE_RESULTS(),parameters,"ARMY_NO=? AND TYPE=? AND WPN=? AND DOF LIKE \"?%\"",args,null,null,null);
			  						 c=DB.indlFirers(args);
			  						 if(c.moveToFirst())   
			  						 {	 
			  							flag=true;
			  						  do
			  						  {  
			  							tr = new TableRow(this); 
			  						   tr.setLayoutParams(new LayoutParams(
			  				                    LayoutParams.FILL_PARENT,
			  				                    LayoutParams.WRAP_CONTENT));   
			  						   	
			  						   //Further editing needed with string taken from cursor
			  	
			  						   //Date
			  				            tv[k][0] = new TextView(this);
			  				            tv[k][0].setId(k*20);
			  				            tv[k][0].setText(c.getString(0));
			  				            tv[k][0].setTextColor(Color.WHITE);
			  				            tv[k][0].setLayoutParams(new LayoutParams(
			  				                    LayoutParams.FILL_PARENT,
			  				                    LayoutParams.WRAP_CONTENT));
			  				            tr.addView(tv[k][0]);
			  				            
			  				            //Wpn
			  				            tv[k][1] = new TextView(this);
			  				            tv[k][1].setId(k*20+1);
			  				            tv[k][1].setText(c.getString(1));
			  				            tv[k][1].setTextColor(Color.WHITE);
			  				            tv[k][1].setLayoutParams(new LayoutParams(
			  				                    LayoutParams.FILL_PARENT,
			  				                    LayoutParams.WRAP_CONTENT));
			  				            tr.addView(tv[k][1]);
			  				            
			  				            //buttno
			  				            tv[k][2] = new TextView(this);
			  				            tv[k][2].setId(k*20+2);
			  				            tv[k][2].setText(c.getString(2));
			  				            tv[k][2].setTextColor(Color.WHITE);
			  				            tv[k][2].setLayoutParams(new LayoutParams(
			  				                    LayoutParams.FILL_PARENT,
			  				                    LayoutParams.WRAP_CONTENT));
			  				            tr.addView(tv[k][2]);
			  					 
			  				            //regno
			  				            tv[k][3] = new TextView(this);
			  				            tv[k][3].setId(k*20+3);
			  				            tv[k][3].setText(c.getString(3));
			  				            tv[k][3].setTextColor(Color.WHITE);
			  				            tv[k][3].setLayoutParams(new LayoutParams(
			  				                    LayoutParams.FILL_PARENT,
			  				                    LayoutParams.WRAP_CONTENT));
			  				            tr.addView(tv[k][3]);
			  				            
			  				            //type
			  				            tv[k][4]= new TextView(this);
			  				            tv[k][4].setId(k*20+4);
			  				            tv[k][4].setText(c.getString(4));
			  				            tv[k][4].setTextColor(Color.WHITE);
			  				            tv[k][4].setLayoutParams(new LayoutParams(
			  				                    LayoutParams.FILL_PARENT,
			  				                    LayoutParams.WRAP_CONTENT));
			  				            tr.addView(tv[k][4]);
			  					 
			  				            //posn
			  				            tv[k][5] = new TextView(this);
			  				            tv[k][5].setId(k*20+5);
			  				            tv[k][5].setText(c.getString(5));
			  				            tv[k][5].setTextColor(Color.WHITE);
			  				            tv[k][5].setLayoutParams(new LayoutParams(
			  				                    LayoutParams.FILL_PARENT,
			  				                    LayoutParams.WRAP_CONTENT));
			  				            tr.addView(tv[k][5]);
			  				            
			  				            //rg
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
			  				            
			  				            //%
			  				            tv[k][8] = new TextView(this);
			  				            tv[k][8].setId(k*20+8);
			  				            
			  				            pt=Float.valueOf(c.getString(7).trim()).floatValue();
			  				            tot=(Float.valueOf(c.getString(8).trim()).floatValue())*3;
			  				            p=(pt/tot)*100;
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
			  				            
			  				            //faults
			  				            faultbutton[k] = new Button(this);
			  				            faultbutton[k].setId(k*20+9);
			  				            faultbutton[k].setTextColor(Color.WHITE);
			  				            faultbutton[k].setText("Faults");
			  				            faults[k]=c.getInt(10);
			  				            faultbutton[k].setOnClickListener(this);
			  				            faultbutton[k].setLayoutParams(new LayoutParams(
			  				                    LayoutParams.FILL_PARENT,
			  				                    LayoutParams.WRAP_CONTENT));
			  				            tr.addView(faultbutton[k]);
			  				            
			  				            //info
			  				            info[k] = new ImageButton(this);
			  				            info[k].setId(k*20+10);
			  				            info[k].setOnClickListener(this);
			  				            //Set info image
			  				            info[k].setLayoutParams(new LayoutParams(
			  				                    LayoutParams.FILL_PARENT,
			  				                    LayoutParams.WRAP_CONTENT));
			  				            tr.addView(info[k]);  
			  				 
			  				            
			  				            
			  				          t1.addView(tr, new TableLayout.LayoutParams(
			  			                    LayoutParams.FILL_PARENT,
			  			                    LayoutParams.WRAP_CONTENT));
			  				          
			  				          k++;
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
			  					 }
			  					 else
			  					 {
			  						Toast.makeText(getBaseContext(), "Error in No.", Toast.LENGTH_LONG).show();
			  					 }
			  					 
			  					break; 
		
			 case R.id.chart:if(flag==false)
				 					Toast.makeText(getBaseContext(), "Error. Please enter the fields required.", Toast.LENGTH_LONG).show();
			 					else
			 					{
			 					 i=new Intent(this,LineChart.class);
			 					i.putExtra("com.app.shikshak.No", numtext.getText());
			 					i.putExtra("com.app.shikshak.Rank", ranktext.getText());
			 					i.putExtra("com.app.shikshak.Name", nametext.getText());
			 					i.putExtra("com.app.shikshak.Subunit", subunittext.getText());
			                    i.putExtra("com.app.shikshak.Type", args[1]);
			                    i.putExtra("com.app.shikshak.Wpn", args[2]);
			                    i.putExtra("com.app.shikshak.Year", args[3]);
			                    
			 					 startActivity(i);
			 					
			 					}
								break;
			 
			 case R.id.back:   i=new Intent(this,FiringAnalysis.class);
			 					startActivity(i);
				                finish();  
				                  break;
			 
			 case R.id.help:
				 
				                  break;
			
			 default: 	
				 		if(flag==true)
				 		{	
				 		
				 		//faults button
				 		 for(int j=0;j<k;j++)
				 		 {
				 		 	if(faultbutton[j].getId()==v.getId())
				 		 	{
				 		 		FAULT=faults[j];
				 		 		showDialog(FAULT_DIALOG);
				 		 		break;
				 		 	}	
				 		 }
				 		 
				 		//history or info button
				 		 for(int j=0;j<k;j++)
				 		 {
				 		 	if(info[j].getId()==v.getId())
				 		 	{
				 		 		/*i=new Intent(this,History.class);
				 		 		i.putExtra("com.app.shikshak.history_no", numtext.getText());
			                    i.putExtra("com.app.shikshak.history_dof",tv[j][0].getText());
			                    i.putExtra("com.app.shikshak.history_regno",tv[j][3].getText());
				 		 		startActivity(i); */
				 		 		break;
				 		 	}	
				 		 }
				 		 
				 		 
				 		 
				 		}
			}
		}
	 
}
