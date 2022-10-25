package sk.stu.fei.appmobv

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import sk.stu.fei.appmobv.data.BusinessDatasource
import sk.stu.fei.appmobv.databinding.ActivityMainBinding
import sk.stu.fei.appmobv.model.Business

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    companion object {
        lateinit var businessList: MutableList<Business>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        businessList = BusinessDatasource().loadBusinessList(this)
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        setupActionBarWithNavController(navController)


    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}