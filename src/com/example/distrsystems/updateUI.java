package com.example.distrsystems;


class updateUI implements Runnable {
	
    private String msg;
    private String user;


 public updateUI(String user,String str) {

     this.msg = str;
     this.user = user;
 }



 @Override

 public void run() {

	 
		communication_activity.updateText(user+msg);

 }

}