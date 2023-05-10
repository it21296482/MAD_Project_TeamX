import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityFuelNewBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class FuelNewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFuelNewBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFuelNewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance().getReference("vehicles")

        binding.addPass.setOnClickListener { insertVehicle() }
    }
    data class Vehicle(
        val ownerName: String,
        val vehiNo: String,
        val nicNo: String,
        val licNo: String,
        val address: String
    )
    private fun insertVehicle() {
        val ownerName = binding.ownerN.text.toString()
        val vehiNo = binding.vehiNo.text.toString()
        val nicNo = binding.nicNumb.text.toString()
        val licNo = binding.licNo.text.toString()
        val address = binding.addrss.text.toString()

        val vehicle = Vehicle(ownerName, vehiNo, nicNo, licNo, address)

        database.child(vehiNo).setValue(vehicle)
            .addOnSuccessListener {
                // Vehicle data saved successfully
            }
            .addOnFailureListener {
                // Error occurred while saving vehicle data
            }
    }
}
