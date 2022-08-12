package com.example.alertdialogboxdemo

import android.app.ProgressDialog.show
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar

/*
Here we will show an example of three different custom dialog boxes that we can use with android.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var constraintLayout: ConstraintLayout
    private lateinit var buttonOne: Button
    private lateinit var buttonTwo: Button
    private lateinit var buttonThree: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
        Never forget to find the ID when you're not using View or DataBinding.
         */
        constraintLayout = findViewById(R.id.constraint_layout)
        buttonOne = findViewById(R.id.button)
        buttonTwo = findViewById(R.id.button2)
        buttonThree = findViewById(R.id.button3)

        /*
        Here is how we create a custom dialog.

        It's pretty self explanatory with written code and the
        built in functionality that comes with Kotlin.

        But we must always create a variable and call the AlertDialog,
        then the Builder class to create the dialog.
         */

        val firstAlertDialog = AlertDialog.Builder(this)
            .setTitle("Alert Dialog One")
            .setIcon(R.drawable.ic_baseline_person_24)
            .setMessage("This is our first AlertDialog box.. Do you like it?")
            .setPositiveButton("Yes!") { _, _ ->
                Snackbar.make( // be sure to pass in the layout that you use.
                    constraintLayout,
                    "You have selected Yes!",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            .setNegativeButton("No") { _, _ ->
                // Here is how we create an UNDO button in Android with a Snackbar.
                Snackbar.make(
                    constraintLayout,
                    "You have selected No!",
                    Snackbar.LENGTH_SHORT
                ).setAction("UNDO") {
                    val snackbar: Snackbar = Snackbar.make(
                        constraintLayout,
                        "Choice Undone!",
                        Snackbar.LENGTH_SHORT
                    )
                    snackbar.show() // we always have to show the snackbar undo button.
                }.show() // showing the entire snackbar
            }
            .create()

        /*
        Once we have created our first ALertDialog, we need to set the
        OnClickListener for the demo and show that dialog when the button is clicked.
         */
        buttonOne.setOnClickListener {
            firstAlertDialog.show() // shows the first alert dialog
        }

        //-----------------------------------------------------------------------------------

        /*
        Now we will show a single choice dialog.

        To do this, we fist need to create an array of Strings.
         */
        val singleChoiceOptions = arrayOf(
            "Michael Jordan", "Kobe Bryant",
            "Hakeem Olajuwon", "Kareem AbdulJabbar"
        )
        val secondDialog = AlertDialog.Builder(this)
            .setTitle("Choice the best player: ")
            // Here we set the singleChoiceItems, which is the only difference.
            .setSingleChoiceItems(singleChoiceOptions, 0) { _, i ->
                Snackbar.make(
                    constraintLayout,
                    "You have selected ${singleChoiceOptions[i]}.",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            .setPositiveButton("Accept") { _, _ ->
                Snackbar.make( // be sure to pass in the layout that you use.
                    constraintLayout,
                    "You accepted the Multi Choice Dialog.",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            .setNegativeButton("Decline") { _, _ ->
                // Here is how we create an UNDO button in Android with a Snackbar.
                Snackbar.make(
                    constraintLayout,
                    "You have Declined this Dialog.",
                    Snackbar.LENGTH_SHORT
                ).setAction("UNDO") {
                    val snackbar: Snackbar = Snackbar.make(
                        constraintLayout,
                        "Choice Undone!",
                        Snackbar.LENGTH_SHORT
                    )
                    snackbar.show() // we always have to show the snackbar undo button.
                }.show() // showing the entire snackbar
            }
            .create()

        /*
        Now we set our onclicklistener for our button2.
         */
        buttonTwo.setOnClickListener {
            secondDialog.show() //
        }

        //-----------------------------------------------------------------------------------------

        /*
        Now we will show how to do a MultiChoiceDialog.

        There honestly isn't much difference between the two.
         */
        val thirdDialog = AlertDialog.Builder(this)
            .setTitle("MultiChoiceDialog")
            // we use a Boolean array to determine which items are checked
            .setMultiChoiceItems(
                singleChoiceOptions, // all items are unchecked.
                booleanArrayOf(false, false, false, false)
            ) { _, i, isChecked -> // i = index
                if (isChecked) {
                    Snackbar.make(
                        constraintLayout,
                        "You checked ${singleChoiceOptions[i]}.",
                        Snackbar.LENGTH_SHORT
                    ).show()
                } else {
                    Snackbar.make(
                        constraintLayout,
                        "You have unchecked${singleChoiceOptions[i]}.",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
            .setPositiveButton("Accept") { _, _ ->
                Snackbar.make( // be sure to pass in the layout that you use.
                    constraintLayout,
                    "You accepted the Single Choice Dialog.",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
            .setNegativeButton("Decline") { _, _ ->
                // Here is how we create an UNDO button in Android with a Snackbar.
                Snackbar.make(
                    constraintLayout,
                    "You have Declined this Dialog.",
                    Snackbar.LENGTH_SHORT
                ).setAction("UNDO") {
                    val snackbar: Snackbar = Snackbar.make(
                        constraintLayout,
                        "Choice Undone!",
                        Snackbar.LENGTH_SHORT
                    )
                    snackbar.show() // we always have to show the snackbar undo button.
                }.show() // showing the entire snackbar
            }
            .create()

        /*
        Now we set our final onclicklistener.
         */
        buttonThree.setOnClickListener {
            thirdDialog.show()
        }
    }
}