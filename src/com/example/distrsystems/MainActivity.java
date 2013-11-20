package com.example.distrsystems;
// what up?
<<<<<<< HEAD
// it should work
// dafuq
=======
//fixed 
// trying git for windows
>>>>>>> 021ffc8146292c2a82408658180e4626df9c95be
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
