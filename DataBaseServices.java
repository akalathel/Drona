package com.app.shikshak;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DataBaseServices extends SQLiteOpenHelper{
	
	private static final String TAG = "DataBaseServices";
	private static final String DB_NAME = "DRONA";
	private static final int DB_VERSION = 1;
	private static final String NO = "ARMY_NO";
	private static final String RANK = "RANK";
	private static final String NAME = "NAME";
	private static final String TRADE = "TRADE";
	private static final String SUBUNIT = "SUBUNIT";
	private static final String DOB = "DOB";
	private static final String DOE = "DOE";
	private static final String WPN ="WPN";
	private static final String REGNO = "REG_NO";
	private static final String BUTTNO = "BUTT_NO";
	
	
	private static final String QTY = "QTY";
	private static final String FIRING_TYPE = "FIRING_TYPE";
	private static final String TOTAL ="TOTAL";
	private static final String GTOTAL = "GTOTAL";
	
	private static final String DB_TABLE_AMMUN = "AMMUN_ALLOT";
	private static final String DB_TABLE_DETAILS="INDL_DET";
	 private static final String DB_TABLE_FIRERS="FIRER_SELEC";
	 private static final String DB_TABLE_WEAPONS="WEAPON";
	 private static final String DB_TABLE_RESULTS="RESULTS";
	private SQLiteDatabase db;
	private Cursor cursor;
	
	
	private static final String MAIN_TABLE_CREATE =

			"CREATE TABLE " +  DB_TABLE_DETAILS +" ( "
			+ NO +" TEXT PRIMARY KEY, "
			+ RANK +" TEXT NOT NULL, "
			+ NAME +" TEXT NOT NULL, "
			+ TRADE + " TEXT NULL,"
			+ SUBUNIT + " TEXT NOT NULL, "
			+ DOB + " DATE NOT NULL, "
			+ DOE + " DATE NOT NULL, "
			+ WPN + " TEXT NOT NULL, "
			+ REGNO + " INTEGER UNIQUE NOT NULL, "
			+ BUTTNO + " INTEGER UNIQUE NOT NULL); ";


	private static final String FIRERS_TABLE_CREATE="CREATE TABLE "+DB_TABLE_FIRERS+"(" +
		NO +" TEXT PRIMARY KEY UNIQUE NOT NULL ); ";
		//+SUBUNIT + " TEXT NOT NULL);";

	
	private static final String ADDL_WPN = "ADDL_WPN";
	private static final String ADDL_BUTTNO = "ADDL_BUTTNO";
	private static final String ADDL_REGNO = "ADDL_REGNO";

private static final String WEAPON_TABLE_CREATE="CREATE TABLE "+DB_TABLE_WEAPONS+"(" +
		NO + " TEXT UNIQUE NOT NULL, "+
		WPN +" TEXT NOT NULL," +
		REGNO +" TEXT NOT NULL,"
		+BUTTNO + " TEXT NOT NULL, "
		+ADDL_WPN +" TEXT NULL, "
		+ADDL_REGNO+" TEXT NULL,"
		+ADDL_BUTTNO+" TEXT NULL" +");";



private static final String AMMUN_TABLE_CREATE="CREATE TABLE "+DB_TABLE_AMMUN+" ( "+
		WPN+ " TEXT UNIQUE PRIMARY KEY NOT NULL, "+
		QTY+ " INTEGER NOT NULL DEFAULT 0, "+
		FIRING_TYPE+ " INTEGER NOT NULL DEFAULT 0, "+
		TOTAL+ " INTEGER NOT NULL DEFAULT 0, "+
		GTOTAL+ " INTEGER NOT NULL DEFAULT 0); ";
	 
	private static final String DOF = "DOF";
	private static final String VISIBILITY = "VIS";
	private static final String TYPE = "TYPE";
	private static final String POS = "POS";
	private static final String TGT = "TGT";
	private static final String RANGE = "RANGE";
	private static final String ROUNDS = "ROUNDS";
	private static final String POINTS = "POINTS";
	private static final String STD = "STD";
	private static final String GPG = "GPG";
	private static final String FAULTS = "FAULTS";
	private static final String TOTPTS = "TOTPTS";

	 private static final String RESULTS_TABLE_CREATE="CREATE TABLE "+DB_TABLE_RESULTS+"(" +
		NO+" TEXT PRIMARY KEY NOT NULL, "+
		DOF +" DATE NOT NULL, "+
		VISIBILITY+" TEXT NOT NULL, " +
		TYPE+" TEXT NOT NULL, "+
		POS+" TEXT NOT NULL, "+
		TGT+" TEXT NOT NULL, "+
		RANGE+" INTEGER NOT NULL, " +
		WPN+" TEXT NOT NULL, "+
		REGNO+" TEXT UNIQUE NOT NULL, "+
		BUTTNO+" INTEGER NOT NULL, "+
		ROUNDS +" INTEGER NOT NULL," +
		//POINTS +" INTEGER NOT NULL," +
		//MISFIRE+ " INTEGER NOT NULL, "+
		TOTPTS+" INTEGER NOT NULL, "+
		GPG+ "TEXT NOT NULL, "+
		FAULTS+" INTEGER NOT NULL, "+
		STD+" TEXT NOT NULL );";
	
	 
	 
	 
	 
public DataBaseServices(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub
	}
	public String getDB_TABLE_DETAILS()
	{
		return DB_TABLE_DETAILS;
	}
	
	public String getDB_TABLE_FIRERS()
	{
		return DB_TABLE_FIRERS;
	}
	
	public String getDB_TABLE_WEAPONS()
	{
		return DB_TABLE_WEAPONS;
	}
	
	public String getDB_TABLE_RESULTS()
	{
		return DB_TABLE_RESULTS;
	}
	
	
	public String getcolNo()
	{
		return NO;
	}
	public String getcolName()
	{
		return NAME;
	}
	public String getcolRank()
	{
		return RANK;
	}
	public String getcolTrade()
	{
		return TRADE;
	}

	public String getcolSubUnit()
	{
		return SUBUNIT;
	}

	public String getcolDOB()
	{
		return DOB;
	}

	public String getcolDOE()
	{
		return DOE;
	}

	public String getcolWpn()
	{
		return WPN;
	}

	public String getcolRegNo()
	{
		return REGNO;
	}

	public String getcolButtNo()
	{
		return BUTTNO;
	}
	
	public String getcolTotal()
	{
		return TOTAL;
	}
	
	public String getcolQty()
	{
		return QTY;
	}
	
	public String getcolGTotal()
	{
		return GTOTAL;
	}
	
	public String getcolFiringType()
	{
		return FIRING_TYPE;
	}
	
	public String getcolADDL_WPN()
	{
		return ADDL_WPN;
	}
	public String getcolADDL_BUTTNO()
	{
		return ADDL_BUTTNO;
	}
	public String getcolADDL_REGNO()
	{
		return ADDL_REGNO;
	}

	

	

	
	
	@Override
	public void onCreate(SQLiteDatabase mdb) {
		// TODO Auto-generated method stub
		mdb.execSQL(MAIN_TABLE_CREATE);
		mdb.execSQL(FIRERS_TABLE_CREATE);
		mdb.execSQL(WEAPON_TABLE_CREATE);
		mdb.execSQL(RESULTS_TABLE_CREATE);
		mdb.execSQL(AMMUN_TABLE_CREATE);
		mdb.execSQL("Insert into "+DB_TABLE_AMMUN+" values('PISTOL AUTO 9MM',0,0,0,0);");
		mdb.execSQL("Insert into "+DB_TABLE_AMMUN+" values('RIF INSAS 5.56 MM',0,0,0,0);");
		mdb.execSQL("Insert into "+DB_TABLE_AMMUN+" values('LMG INSAS 5.56MM',0,0,0,0);");
		mdb.execSQL("Insert into "+DB_TABLE_AMMUN+" values('CMG 9 MM',0,0,0,0);");
		mdb.execSQL("Insert into "+DB_TABLE_AMMUN+" values('LMG 7.62 MM',0,0,0,0);");
		mdb.execSQL("Insert into "+DB_TABLE_AMMUN+" values('AK 47/56',0,0,0,0);");
		
		
		
		
		
	}
	
	


	@Override
	public void onUpgrade(SQLiteDatabase mdb, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		Log.w(TAG, "Upgrading database from version " + oldVersion + " to "
                + newVersion + ", which will destroy all old data");
        mdb.execSQL("DROP TABLE IF EXISTS "+"MAIN_TABLE_NAME");
        onCreate(mdb);
	}
	public void openForWrite() throws SQLiteException
	 {
		 try
		 {
			 db=this.getWritableDatabase();
		 }
		 catch(SQLiteException ex)
		 {
			 Log.e(TAG, "couldn't get a writale handle to DB");
			// db=this.getReadableDatabase();
		 }
	 }
	public void openForRead() throws SQLiteException
	 {
		 try
		 {
			 db=this.getReadableDatabase();
		 }
		 catch(SQLiteException ex)
		 {
			 Log.e(TAG, "couldn't get a writale handle to DB");
			// db=this.getReadableDatabase();
		 }
	 }

	 public long insertMainDb(String no,
			 				  String rank, 
			 				  String name,
			 				  String trade,
			 				  String subunit,
			 				  String dob,
			 				  String doe,
			 				  String wpn,
			 				  String regno,
			 				  String buttno) throws SQLException
		{ 
		     try{
		    	 openForWrite();
		     
			 ContentValues cv=new ContentValues();
			   cv.put(NO, no);
			   cv.put(RANK, rank);
			   cv.put(NAME, name);
			   cv.put(TRADE, trade);
			   cv.put(SUBUNIT, subunit);
			   cv.put(DOB, dob);
			   cv.put(DOE, doe);
			   cv.put(WPN, wpn);
			   cv.put(REGNO, regno);
			   cv.put(BUTTNO, buttno);
			   return db.insert(DB_TABLE_DETAILS, TRADE, cv);
		     }
		     catch (SQLiteException e) { 
		       
		             if (db != null) 
		                  db.close(); 
		             return (Long)null;
		     }
		} 
	 
	 
	 public void close() {
		 
		 if(db!=null)
	        db.close();
	    }
	 public Cursor queryM(String SQL, 
			 	  String[] projectionIn, 
			 	  String selection, 
			 	  String[] selectionArgs, 
			 	  String groupBy, 
			 	  String having, 
			 	  String orderBy)
	 {
		try{
			openForRead();
		
		return db.query (SQL, projectionIn, selection, selectionArgs, groupBy, having, orderBy);
		}
		catch (SQLiteException e) { 
		       
            if (db != null) 
                 db.close(); 
            return (Cursor)null;
    }
	 }
	 
	 
	 
	 
	 
	 
	 public Cursor countWPN(String table,String wpn)
     {
     	openForRead();
     	
			String a="SELECT * FROM "+table+ " WHERE "+getcolWpn()+"="+"'"+wpn+"'"+";";
     	return db.rawQuery(a, null);
     }
     
     
    public int inserAmmunationDetails(String wpn,int qty,int fire_type,int total,int gtotal)
    {
 	  
		    	 openForWrite();
		     
			 ContentValues cv=new ContentValues();
			   cv.put(QTY, qty);
			   cv.put(FIRING_TYPE, fire_type);
			   cv.put(TOTAL, total);
			   cv.put(GTOTAL, gtotal);
			   
			   return db.update(DB_TABLE_AMMUN, cv, WPN + "=?", new String[]{wpn});
		     
    }
    
    public Cursor getAmmunDetails(String wpn)
    {
 	   openForRead();
 	   String a="SELECT * FROM "+DB_TABLE_AMMUN+ " WHERE "+getcolWpn()+"="+"'"+wpn+"'"+";";
    	return db.rawQuery(a, null);
    }

    
    public void deleteSub(String table,String where,String[] whereArgs) throws SQLException
	{
		//String a[] = null;
		//a[0]=new String();
		//a[0]=s;
		openForWrite();
       // db.delete(DB_TABLE_DETAILS,"SUBUNIT=?" ,a )    ; 
		db.delete(table, where, whereArgs);
	 	// db.execSQL("DELETE FROM " + DB_TABLE_DETAILS + " WHERE SUBUNIT=" + s);
		close(); 
	}
    
    public void deleteAll(String table) throws SQLException 
	 {
		 openForWrite();
		 //db.execSQL("DELETE FROM " + DB_TABLE_DETAILS);
          db.delete(table, null, null);
                close();
	 }
    
    public void deleteIndl(String table,String where,String[] whereArgs) throws SQLException
	{
        	//String a[] = null;
    		//a[0]=new String();
    		//a[0]=s;	
	 	openForWrite();
        //db.delete(DB_TABLE_DETAILS, "ARMY_NO=?", a);    
	 	db.delete(table, where, whereArgs);
	 	 //db.execSQL("( DELETE FROM " + DB_TABLE_DETAILS + " WHERE ARMY_NO='" + s+"'"+" );");
		close(); 
	}
        
        public Cursor askquery(String table, String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy)
    	{
        	openForRead();
    		Cursor c=db.query(table,columns,selection,selectionArgs,groupBy,having,orderBy);
    		
    		return c;
    	} 

	 
       
        
//------------------------------------------
	
	 
	 public boolean chkIfEmpty(String s)
	 {
		 this.openForRead();
	  cursor= db.rawQuery("SELECT count(*) FROM "+s,null);
	  cursor.moveToFirst();
	  if(cursor.getString(0).equals("0"))
	  {
	   cursor.close();
	   return true;  //empty
	  }
	  else
	  {
	   cursor.close();
	   return false; //not empty
	  }
	 }
	 
	 
	 
	 public Cursor getFirersOfASubunitAndWeapon(String s,String wpn)
	 {
		 this.openForRead();
	  if(s!=null && wpn!=null)
	  cursor=db.rawQuery("SELECT " +
			  DB_TABLE_DETAILS+"."+NO+", "+
			  DB_TABLE_DETAILS+"."+RANK+", "+
			  DB_TABLE_DETAILS+"."+NAME+", "+
			  DB_TABLE_DETAILS+"."+SUBUNIT+" "+
			  //find last fired
			  //find STD
			  " FROM "+DB_TABLE_DETAILS+
			  //" , "+DB_TABLE_RESULTS+
			  " WHERE "+DB_TABLE_DETAILS+"."+WPN+"=?"+
			  " AND "+
			  DB_TABLE_DETAILS+"."+SUBUNIT+"=?" 
	  		 , new String[] {wpn,s });
	 
	  	  
	  return cursor;
	 }

	 
	 public void insertFirer(String no)
	 {
		 try{
	    	 openForWrite();
	     
		 ContentValues cv=new ContentValues();
		   cv.put(NO, no);
		   
		   db.insert(DB_TABLE_FIRERS, null, cv);
	     }
	     catch (SQLiteException e) { 
	       
	             if (db != null) 
	                  db.close(); 
	             Log.e("FIRER", "couldn't add to the table");
	     }
	 }
	 
	 
	 public Cursor getFirersQuery(String wpn,
			 String rank,
			 String sunit,
			 String trade,
			 String percent,
			 String dof)
	 {
		 this.openForRead();
		 boolean flag=false;
		 String q ="SELECT " +
		  DB_TABLE_DETAILS+"."+NO+", "+
		  DB_TABLE_DETAILS+"."+RANK+", "+
		  DB_TABLE_DETAILS+"."+NAME+", "+
		  DB_TABLE_DETAILS+"."+BUTTNO+", "+
		  DB_TABLE_DETAILS+"."+REGNO+" "+
		  //find last fired
		  //find %
		  //find STD
		  " FROM "+DB_TABLE_DETAILS;
		  //" , "+DB_TABLE_RESULTS+
		  
		  if((wpn!=null && !wpn.equals("ALL")) || rank!=null || (sunit!=null && !sunit.equals("ALL")) || ( trade!=null && !trade.equals("ALL")) )
		  {
			  q=q+" WHERE ";
		  }
		  
		 
		 if(wpn!=null && !wpn.equals("ALL"))
		 {
			
			 q=q+ DB_TABLE_DETAILS+"."+WPN+"=\""+wpn+"\" ";
			 flag=true;
		 }
		 if(rank!=null)
		 {
			 if ( flag)
			 q=q+" AND "+ DB_TABLE_DETAILS+"."+RANK+"=\""+rank+"\" ";
			 else
			q=q+ DB_TABLE_DETAILS+"."+RANK+"=\""+rank+"\" ";
		 }
		 if(sunit!=null && !sunit.equals("ALL"))
		 {
			 if ( flag)
				 q=q+ " AND "+DB_TABLE_DETAILS+"."+SUBUNIT+"=\""+sunit+"\" ";
			 else
			 q=q+ DB_TABLE_DETAILS+"."+SUBUNIT+"=\""+sunit+"\" ";
		 }
		 if(trade!=null && !trade.equals("ALL"))
		 {
			 if(flag)
				 q=q+ " AND "+DB_TABLE_DETAILS+"."+TRADE+"=\""+trade+"\" ";
			 else
			 q=q+ DB_TABLE_DETAILS+"."+TRADE+"=\""+trade+"\" ";
		 }
		 q=q+" ;";
		 
				 //add %, DOF to be checked
		return db.rawQuery(q, null);		
		 
		 
	 }
	 
	 public boolean update(String oldNO,String no,String rank,String name,String trade,String subunit,
             String dob,String doe,String wpn,int regno,int buttno,boolean tradeFlag) throws SQLiteException
	 {
		 openForWrite();
		 ContentValues cv=new ContentValues();
		 if(no!=null)
		 cv.put(NO, no);
		 
		 if(rank!=null)
		  cv.put(RANK, rank);
		 
		 if(name!=null)
		  cv.put(NAME, name);
		 
		 if(tradeFlag) 
		 cv.put(TRADE, trade);
		  
		  if(subunit!=null)
		  cv.put(SUBUNIT, subunit);
		  
		  if(dob!=null)
		  cv.put(DOB, dob);
		  
		  if(doe!=null)
		  cv.put(DOE, doe);
		  
		  if(wpn!=null)
		  cv.put(WPN, wpn);
		  
		  if(regno!=-1)
		  cv.put(REGNO, regno);
		  
		  if(buttno!=-1)
		  cv.put(BUTTNO, buttno);
		  return db.update(DB_TABLE_DETAILS, cv, NO + "=?", new String[]{oldNO})>0;
	 }
	 
	 
	 public Cursor getOneSoldierDetail(String s) throws SQLException
	 {
		 openForRead();
		 cursor=db.rawQuery("SELECT COUNT(*) FROM "+DB_TABLE_DETAILS+" WHERE "+NO+"=?", new String[]{s});
		 cursor.moveToFirst();
		 if(!(cursor.getString(0).equals("0")))
	    {
	      cursor.close();
		  cursor=db.query(DB_TABLE_DETAILS, new String[]{RANK,NAME,TRADE,SUBUNIT,DOB,DOE,WPN,REGNO,BUTTNO},
				  NO + "=?", new String[]{s}, null, null, null); 
		
		  		 }		   
		 
		  if((cursor.getCount()==0)||!cursor.moveToFirst())
			 throw new SQLException("No record found with the given id in the database.");
		 return cursor;
	 }
	 
	 
	 
     public Cursor getAllSoldierDetails() throws SQLException
  	 {
    	   openForRead();
  		 cursor=db.query(DB_TABLE_DETAILS, null, null, null, null, null, null);
  		 if((cursor.getCount()==0)||!cursor.moveToFirst())
  			 throw new SQLException("No records in the database.");
  		 return cursor;
  	 }
       
     public Cursor getSelectedFirers()
	 {
  		openForRead();
		 cursor=db.query(DB_TABLE_FIRERS, new String[]{NO}, null, null, null, null, null);
		 return cursor;
	 }
     
     public Cursor getIndlWpns(String s)
  	 {
    	   openForRead();
  	  cursor=db.query(DB_TABLE_DETAILS, new String[]{RANK,NAME,WPN,BUTTNO,REGNO}, NO+"=?", new String[]{s}, null, null,null);
  	  return cursor;
  	 }
     
	 public int noOfRows(String s)
	 {
		 this.openForRead();
		 cursor=db.rawQuery("SELECT count(*) FROM "+s,null);
		 cursor.moveToFirst();
		 return cursor.getInt(0);
	 }
	 
	public Cursor getWpns()
	 {
		 this.openForRead();
		 cursor=db.query(DB_TABLE_WEAPONS, new String[] {NO}, null, null, null, null, null);
		 return cursor;
	 }
	 
	 public Cursor getWpnsOfFirers()
	 {
		 this.openForRead();
		 cursor=db.query(DB_TABLE_FIRERS, new String[] {NO}, null, null, null, null, null);
		 return cursor;
	 }
	 public void updateAddlWpn(String no,String wpn,String butt,String reg)
	 {
		 
		 
		 openForWrite();
	  ContentValues cv=new ContentValues();
	  cv.put(ADDL_WPN,wpn);
	  cv.put(ADDL_BUTTNO,butt);
	  cv.put(ADDL_REGNO,reg);
	   db.update(DB_TABLE_WEAPONS,cv,NO+"=?",new String[]{no});
	  
	 }  
	 
	 public void updateWpn(String no,String wpn,String butt,String reg)
	 {
		 
		 
		 openForWrite();
	  ContentValues cv=new ContentValues();
	  cv.put(WPN,wpn);
	  cv.put(BUTTNO,butt);
	  cv.put(REGNO,reg);
	   db.update(DB_TABLE_WEAPONS,cv,NO+"=?",new String[]{no});
	  
	 }  
  	 
	 
	 
	 public void allocateWpn(String no,String wpn,String butt,String reg)
	 {
		 
		 
		 openForWrite();
		 if(wpn.equals("NOT ALLOCATTED"))
		 {
			 return;
		 }
		 cursor =db.rawQuery("SELECT "+NO+" FROM "+DB_TABLE_WEAPONS+" WHERE "+NO+"=\""+no+"\" ;",null);
		 if(cursor!=null)
			 cursor.moveToFirst();
		 if(cursor.getCount()==0)
			 	db.execSQL( "INSERT INTO "+DB_TABLE_WEAPONS+" ("+NO+","+WPN+","
			 			  +BUTTNO+","+REGNO+") VALUES(\""+no+"\",\""+wpn+
			 			  "\",\""+butt+"\",\""+reg+"\" ) ;");
		 else
		 {
			 
			  ContentValues cv=new ContentValues();
			  cv.put(WPN,wpn);
			  cv.put(BUTTNO,butt);
			  cv.put(REGNO,reg);
			   db.update(DB_TABLE_WEAPONS,cv,NO+"=?",new String[]{no});
		 }
			  
	  
	 }  
  	 
	 public Cursor firersForADetail(String s)
	 {
	  cursor=db.rawQuery("SELECT count(*) FROM "+DB_TABLE_WEAPONS+" WHERE "+WPN+"=? AND "+NO+"=?",new String[]{s,"0"});//why 0

	  cursor.moveToFirst();
	  if(!cursor.getString(0).equals("0"))
	  {
	   cursor.close();
	   cursor=db.query(DB_TABLE_WEAPONS, new String[]{NO,WPN,BUTTNO,REGNO}, WPN+"=? AND "+NO+"=?", new String[]{s,"0"}, null, null, null);
	  }
	  return cursor;
	 }

	 public Cursor getDetailsOfSunitFirers(String s)
	 {
	  cursor=db.query(DB_TABLE_DETAILS, new String[]{RANK,NAME,SUBUNIT}, NO+"=?",new String[]{s}, null, null, null);
	  return cursor;
	 }
	 
	 public Cursor getADetail(int ctr)
	 {
		 cursor=db.query(DB_TABLE_WEAPONS, new String[] {NO,WPN,BUTTNO,REGNO}, NO+"=?", new String[]{Integer.toString(ctr)}, null, null, null);
		 return cursor;
	 }
	 
	 public void updDetailNo(String s,int ctr)
	 {
	  ContentValues cv=new ContentValues();
	  cv.put(NO,ctr);
	  if(s!=null)
		  db.update(DB_TABLE_WEAPONS, cv, NO+"=?", new String[]{s});
	  else
		  db.update(DB_TABLE_WEAPONS, cv, null, null);
	 }
	 
	 public Cursor getOtherDetails(int n)
	 {
		 cursor=db.query(DB_TABLE_WEAPONS, new String[] {NO,WPN,BUTTNO,REGNO}, NO+"!=?", new String[]{Integer.toString(n)}, null, null, null);
		 return cursor;
	 }
	 
	 public Cursor getANo()
	 {
		 cursor=db.query(DB_TABLE_WEAPONS, new String[]{NO}, NO+"=?", new String[]{"0"}, null, null, null, "1");
		 return cursor;
	 }
	 
	 
	 public Cursor getDetailNo(String s)
	 {
		 cursor=db.query(DB_TABLE_WEAPONS, new String[]{NO}, NO+"=?", new String[]{s}, null, null, null);
		 return cursor;
	 }
	 
	 public void clearFirerSelection()
	 {
		 try
		 {
		 openForWrite();
		 db.delete(DB_TABLE_FIRERS,null,null);
		 }
		 catch (SQLiteException e) { 
		       
             if (db != null) 
                  db.close(); 
            
     }
		 
	 }
	 
	 
	 public Cursor getIndlWpnsWithoutButt(String no){
		 openForRead();
		 
		 return db.query(DB_TABLE_DETAILS, new String[]{RANK,NAME,WPN}, NO+"=?", new String[]{no}, null, null,null);
	  	  
		 
	 }
	 
	 
	 public Cursor getDetailsOfFirersWithWeaponAlloted(String no)
	 {
		
		
		return db.rawQuery("SELECT "
				  +DB_TABLE_DETAILS+"."+RANK+", "
				  +DB_TABLE_DETAILS+"."+NAME+", "
				  +DB_TABLE_WEAPONS+"."+WPN+", "
				  +DB_TABLE_WEAPONS+"."+BUTTNO+", "
				  +DB_TABLE_WEAPONS+"."+REGNO+" "
				  +"FROM " +DB_TABLE_DETAILS+", "+DB_TABLE_WEAPONS +" WHERE "+
				  DB_TABLE_WEAPONS+"."+NO+"=\""+no+"\" "+
				  " AND " +
				  DB_TABLE_DETAILS+"."+NO+"=\""+no+"\""+" ;", null);
		 
		 
	 }
	 
	public Cursor getWpnAllocatted(String no)
	{
		openForRead();
			String s="SELECT "+WPN+" FROM " +DB_TABLE_WEAPONS+" WHERE "+
	  		NO+"=\""+no+"\""+" ;";
		  return db.rawQuery(s, null);
		 

	}
	 
	 
	public void clearWeaponTable() {
		openForWrite();
		db.execSQL("DELETE FROM "+DB_TABLE_WEAPONS+" ;");
		
	}
	public Cursor getDetailsofWpnAllocation() {
		
		openForRead();
		
		 return db.rawQuery("SELECT "
				 +DB_TABLE_DETAILS+"."+NO+", "
				  +DB_TABLE_DETAILS+"."+RANK+", "
				  +DB_TABLE_DETAILS+"."+NAME+", "
				  +DB_TABLE_WEAPONS+"."+WPN+", "
				  +DB_TABLE_WEAPONS+"."+BUTTNO+", "
				  +DB_TABLE_WEAPONS+"."+REGNO+", "
				  +DB_TABLE_WEAPONS+"."+ADDL_WPN+", "
				  +DB_TABLE_WEAPONS+"."+ADDL_BUTTNO+", "
				  +DB_TABLE_WEAPONS+"."+ADDL_REGNO+" "
				  +"FROM " +DB_TABLE_DETAILS+", "+DB_TABLE_WEAPONS +
				  " WHERE "+DB_TABLE_DETAILS+"."+NO+"="+DB_TABLE_WEAPONS+"."+NO+" ;", null);
		
	}
	
	public Cursor getDetailsOfSelectedFirers()
	{
		openForRead();
		
		String s ="SELECT "+
		   DB_TABLE_FIRERS+"."+NO+", "
		  +DB_TABLE_DETAILS+"."+RANK+", "
		  +DB_TABLE_DETAILS+"."+NAME+", "
		  +DB_TABLE_DETAILS+"."+TRADE+", "
		  +DB_TABLE_DETAILS+"."+SUBUNIT+" "
		  +"FROM " +DB_TABLE_DETAILS+", "+DB_TABLE_FIRERS +
		  " WHERE "+DB_TABLE_DETAILS+"."+NO+"="+DB_TABLE_FIRERS+"."+NO+" ;";
		
		return db.rawQuery(s, null);
		  
		  
	}
	
	public Cursor indlFirers(String[] args) 
	{
		openForRead();
   		String s;
   		s="SELECT "
   				  +DB_TABLE_RESULTS+"."+DOF+", "
   				  +DB_TABLE_RESULTS+"."+WPN+", "
   				  +DB_TABLE_RESULTS+"."+BUTTNO+", "
   				  +DB_TABLE_RESULTS+"."+REGNO+", "
   				  +DB_TABLE_RESULTS+"."+TYPE+", "
   				  +DB_TABLE_RESULTS+"."+POS+", "
   				  +DB_TABLE_RESULTS+"."+RANGE+", "
   				  +DB_TABLE_RESULTS+"."+TOTPTS+", "
 				  +DB_TABLE_RESULTS+"."+ROUNDS+", "
 				  +DB_TABLE_RESULTS+"."+STD+", "
 				  +DB_TABLE_RESULTS+"."+FAULTS+" "

 				  
   				  +"FROM " +DB_TABLE_RESULTS 
   				  +" WHERE "+
   				  DB_TABLE_RESULTS+"."+NO+"=\""+args[0]+"\" ";
   				  
   				
   				if(!args[1].equals("ALL"))
   				{
   					s.concat(" AND " + DB_TABLE_RESULTS+"."+TYPE+"=\""+args[1]+"\" ");
   				}
   				if(!args[2].equals("ALL"))
   				{
   					s.concat(" AND " + DB_TABLE_RESULTS+"."+WPN+"=\""+args[2]+"\" ");
   				}  
   				if(!args[3].equals("ALL"))
   				{
   					s.concat(" AND " + DB_TABLE_RESULTS+"."+DOF+" LIKE \""+args[3]+"%\" ");
   				}  
				s.concat(";") ;
  				
   		return db.rawQuery(s,null);
	}
	 
	 //FOR OVERALL STANDARDS IN ANALYSIS
    public Cursor overallFirers(String []args)
	 {
		openForRead();
		String s;
		s="SELECT "
				  +DB_TABLE_DETAILS+"."+NO+", "
				  +DB_TABLE_DETAILS+"."+RANK+", "
				  +DB_TABLE_DETAILS+"."+NAME+", "
				  +DB_TABLE_DETAILS+"."+SUBUNIT+", "
				  
				  +DB_TABLE_RESULTS+"."+WPN+", "
				  +DB_TABLE_RESULTS+"."+BUTTNO+", "
				  +DB_TABLE_RESULTS+"."+REGNO+", "
				  +DB_TABLE_RESULTS+"."+TOTPTS+", "
				  +DB_TABLE_RESULTS+"."+ROUNDS+", "
				  +DB_TABLE_RESULTS+"."+STD+", "
				  +DB_TABLE_RESULTS+"."+DOF+" "

				  
				  +"FROM " +DB_TABLE_DETAILS+", "+DB_TABLE_RESULTS 
				  +" WHERE "+
				  DB_TABLE_RESULTS+"."+NO+"="+DB_TABLE_DETAILS+"."+NO+" ";
				  
				if(!args[0].equals("ALL"))
				{
					s.concat(" AND " + DB_TABLE_DETAILS+"."+SUBUNIT+"=\""+args[0]+"\" ");
				}
				if(!args[1].equals("ALL"))
				{
					s.concat(" AND " + DB_TABLE_RESULTS+"."+WPN+"=\""+args[1]+"\" ");
				}
				if(!args[2].equals("ALL"))
				{
					s.concat(" AND " + DB_TABLE_RESULTS+"."+TYPE+"=\""+args[2]+"\" ");
				}  
				if(!args[3].equals("ALL"))
				{
					s.concat(" AND " + DB_TABLE_RESULTS+"."+STD+"=\""+args[3]+"\" ");
				}    
				if(!args[4].equals("BOTH"))
				{
					s.concat(" AND " + DB_TABLE_RESULTS+"."+VISIBILITY+"=\""+args[4]+"\" ");
				}      
				//if(args[5].equals("ALL"))
				//{
					s.concat(" AND " + DB_TABLE_RESULTS+"."+DOF+" LIKE \""+args[5]+"%\" ");
				//}        
				  
			s.concat(";") ;
				
		return db.rawQuery(s,null);
	 }
    
    public Cursor getIndlPointsOverAPeriod(String no,String fromDate,String toDate,String type,String wpn)
    {
     openForRead();
     int f1=0,f2=0;
     String condition="NO=? AND DOF>=? AND DOF<=?";
     if(!type.equals("ALL"))
     {
      f1=1;
      condition+=" AND TYPE=?";
     }
     if(!wpn.equals("ALL"))
     {
      f2=1;
      condition+=" AND WPN=?";
     }
     String params[]=new String[]{no,fromDate,toDate};
     if(f1==1)
     {
      params[3]=type;
      if(f2==1)
       params[4]=wpn;
     }
     else if(f2==1)
      params[3]=wpn;
     cursor=db.query(DB_TABLE_RESULTS, new String[]{"DOF","POINTS"}, condition, params, null, null, null);
     return cursor;

    }
	
}



