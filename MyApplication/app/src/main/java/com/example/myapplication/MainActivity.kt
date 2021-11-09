

package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    // Initialise the DrawerLayout, NavigationView and ToggleBar
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarToggle: ActionBarDrawerToggle
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // set text view and button
        setContentView(R.layout.activity_main)

        var txtView = findViewById<TextView>(R.id.txtView)
        var editTxt = findViewById<TextView>(R.id.editText)
//        editTxt.setText(R.string.welcome)
//        editTxt.setTextColor(getResources().getColor(R.color.myColor))
        var btn = findViewById<Button>(R.id.btnShow)
        btn.setOnClickListener{
            txtView.setText(editTxt.text)
            Toast.makeText(this, editTxt.text, Toast.LENGTH_SHORT).show()
        }

        // bottom navigation bar
        var bottomNavigationView : BottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home-> {
                    Toast.makeText(this,"Home",Toast.LENGTH_SHORT).show()
                }
                R.id.search-> {
                    Toast.makeText(this,"Search",Toast.LENGTH_SHORT).show()
                }
                R.id.setting-> {
                    Toast.makeText(this,"Setting",Toast.LENGTH_SHORT).show()
                }

            }
            true
        }

        // drawer menu part 2
        // Call findViewById on the DrawerLayout
        var actionBar = supportActionBar
        actionBar!!.title = "My Activity"
        actionBar.setDisplayHomeAsUpEnabled(true)
        drawerLayout = findViewById(R.id.drawerLayout)

        // Pass the ActionBarToggle action into the drawerListener
        actionBarToggle = ActionBarDrawerToggle(this, drawerLayout, 0, 0)
        drawerLayout.addDrawerListener(actionBarToggle)

        // Display the hamburger icon to launch the drawer
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // Call syncState() on the action bar so it'll automatically change to the back button when the drawer layout is open
        actionBarToggle.syncState()


        // Call findViewById on the NavigationView
        navView = findViewById(R.id.navView)

        // Call setNavigationItemSelectedListener on the NavigationView to detect when items are clicked
        navView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.myProfile -> {
                    Toast.makeText(this, "My Profile", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.people -> {
                    Toast.makeText(this, "People", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.settings -> {
                    Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show()
                    true
                }
            }
            // close drawer
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    // drawer menu part 3
    // override the onSupportNavigateUp() function to launch the Drawer when the hamburger icon is clicked
    override fun onSupportNavigateUp(): Boolean {
        this.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    // override the onBackPressed() function to close the Drawer when the back button is clicked
    override fun onBackPressed() {
        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    // top menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_top , menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item != null)
            when(item.itemId){
                R.id.kotlin -> {
                    Toast.makeText(this,"What is Kotlin ?\n" +
                            "Kotlin is a statically-typed programming language for modern multi-platform applications.  Kotlin was developed by JetBrains, a company acclaimed for developing tools for professionals. The foremost goal of Kotlin is to provide a concise, productive and safer alternative to Java. The most common areas to use Kotlin are\n" +
                            "Building server-side code\n" +
                            "Building mobile applications that run on Android devices",Toast.LENGTH_LONG).show()
                }
                R.id.info -> {
                    Toast.makeText(this,"GitHub.com/alirezsbashi",Toast.LENGTH_SHORT).show()
                }
                R.id.about -> {
                    Toast.makeText(this,"im AlirezaBashi programming android",Toast.LENGTH_SHORT).show()
                }
                android.R.id.home -> {
                    drawerLayout.openDrawer(GravityCompat.START)
                    true
                }
            }
        return true
    }
}