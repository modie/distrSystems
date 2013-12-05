package com.example.distrsystems;


class updateUIThread implements Runnable {
	
    private String msg;
    private String user;


 public updateUIThread(String user,String str) {

     this.msg = str;
     this.user = user;
 }



 @Override

 public void run() {

	 
		communication_activity.updateText(user+msg);

 }

}