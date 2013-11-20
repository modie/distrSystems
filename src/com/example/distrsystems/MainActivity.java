package com.example.distrsystems;
// what up?
<<<<<<< HEAD
//still testing
=======
>>>>>>> 40d501c7f938cf315083c34267c114452ae62351
// it should work
// dafuq
//fixed 
// trying git for windows
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
