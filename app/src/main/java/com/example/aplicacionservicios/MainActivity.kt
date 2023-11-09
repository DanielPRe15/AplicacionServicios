package com.example.aplicacionservicios

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionservicios.adaptador.ServicioAdapter
import com.example.aplicacionservicios.base.InitBD
import com.example.aplicacionservicios.controlador.ArregloServicio
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawer: DrawerLayout
    private lateinit var toggle: ActionBarDrawerToggle


    private lateinit var rvServicio:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menu_principal)

        rvServicio=findViewById(R.id.rvServicio)

        /*val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar_main)
        setSupportActionBar(toolbar)

        drawer = findViewById(R.id.drawer_layout)

        toggle = ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer.addDrawerListener(toggle)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        val navigationView: NavigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)*/


    var datos = ArregloServicio().listado()

    //crear objeto de la clase DocenteAdapter
    var adaptador = ServicioAdapter(datos)
    //estilo tipo fila para visualizar datos en rvDocentes
    rvServicio.layoutManager=LinearLayoutManager(this)
    //rvServicio.layoutManager=GridLayoutManager(this,2)
    //mostrar el valor del objeto adaptador al atributo rvDocentes
    rvServicio.adapter=adaptador
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId){
            R.id.nav_item_one -> Toast.makeText(this, "Item 1", Toast.LENGTH_SHORT).show()
            R.id.nav_item_two -> Toast.makeText(this, "Item 2", Toast.LENGTH_SHORT).show()
            R.id.nav_item_three -> Toast.makeText(this, "Item 3", Toast.LENGTH_SHORT).show()
        }

        drawer.closeDrawer(GravityCompat.START)
        return true

    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)
        toggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        toggle.onConfigurationChanged(newConfig)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}