package my.edu.tarc.bmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import my.edu.tarc.bmi.databinding.ActivityMainBinding
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    // Module level variable here
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // inflate = display the UI
        binding = ActivityMainBinding.inflate(layoutInflater)

        // setContentView(R.layout.activity_main)
        // root referring to top of a tree structure -> layout
        setContentView(binding.root)

        // Local variable here
        binding.buttonReset.setOnClickListener {
            binding.editTextHeight.setText("")
            binding.editTextWeight.setText("")
            binding.imageViewBMI.setImageResource(R.drawable.empty)
            binding.textViewBMI.text = "@string/app_name"
        }
        binding.buttonCalculate.setOnClickListener {
            if (binding.editTextHeight.text.isEmpty()) {
                binding.editTextHeight.setError(getString(R.string.value_required))
                return@setOnClickListener
            }

            if (binding.editTextWeight.text.isEmpty()) {
                binding.editTextWeight.setError(getString(R.string.value_required))
                return@setOnClickListener
            }

            val weight = binding.editTextWeight.text.toString().toFloat()
            val height = binding.editTextHeight.text.toString().toFloat()
            val bmi = weight / (height / 100).pow(2)

            if (bmi < 18.5) {
                // underweight
                binding.textViewBMI.text =
                    String.format("%s:%.2f (%s)", getString(R.string.app_name),
                        bmi, getString(R.string.underweight))
                binding.imageViewBMI.setImageResource(R.drawable.under)
            } else if (bmi <= 24.9) {
                // normal
                binding.textViewBMI.text =
                    String.format("%s:%.2f (%s)", getString(R.string.app_name),
                        bmi, getString(R.string.normal))
                binding.imageViewBMI.setImageResource(R.drawable.normal)
            } else {
                // overweight
                binding.textViewBMI.text =
                    String.format("%s:%.2f (%s)", getString(R.string.app_name),
                        bmi, getString(R.string.overweight))
                binding.imageViewBMI.setImageResource(R.drawable.over)
            }
        }
    }
}