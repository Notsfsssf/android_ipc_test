package com.perol.example

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.widget.Toolbar
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = LinearLayout(this)
        view.orientation = LinearLayout.VERTICAL
        val button = Button(this)
        button.setOnClickListener {
            startActivity(Intent(this, MessengerActivity::class.java))
        }
        button.text = "MessengerService"
        val button2 = Button(this)
        button2.text = "AIDLService"
        button2.setOnClickListener {
            startActivity(Intent(this, AIDLActivity::class.java))
        }
        val button3 = Button(this)
        button3.text = "Socket"
        button3.setOnClickListener {
            startActivity(Intent(this, SocketActivity::class.java))
        }
        val toolbar = Toolbar(this)
        view.addView(toolbar)
        view.addView(button)
        view.addView(button2)
        view.addView(button3)
        view.addView(Button(this).apply {
            text = "BroadCast"
            setOnClickListener {
                startActivity(Intent(this@MainActivity, BroadCastActivity::class.java))
            }
        })
        setContentView(view)
        setSupportActionBar(toolbar)


    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}