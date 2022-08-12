package com.example.alertdialogboxdemo

import android.Manifest
import android.app.ProgressDialog.show
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
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
            .setMessage("Would you like to give this application access to Location and Storage?")
            .setPositiveButton("Yes") { _, _ ->
                Snackbar.make( // be sure to pass in the layout that you use.
                    constraintLayout,
                    "You have selected Yes...",
                    Snackbar.LENGTH_SHORT
                ).show()
                requestPermissions() // demo for permissions.
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
        firstAlertDialog.show()
        /*
        Once we have created our first ALertDialog, we need to set the
        OnClickListener for the demo and show that dialog when the button is clicked.
         */

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
            .setTitle("Basketball Players")
            .setMessage("Choose your favorite player: ")
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
            /*
                Here is how we can call permissions using the neutral button.
                 */
            .setNeutralButton("Permissions") {_, _ ->
                requestPermissions()
            }
            .create()

        /*
        Now we set our onclicklistener for our button2.
         */
        buttonTwo.setOnClickListener {
            secondDialog.show()
        }

        //-----------------------------------------------------------------------------------------

        /*
        Now we will show how to do a MultiChoiceDialog.

        There honestly isn't much difference between the two.
         */
        val thirdDialog = AlertDialog.Builder(this)
            .setTitle("Basketball MultiChoice")
            .setMessage("Choose your favorite player: ")
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
            /*
               Here is how we can call permissions using the neutral button.
                */
            .setNeutralButton("Permissions") {_, _ ->
                requestPermissions()
            }
            .create()

        /*
        Now we set our final onclicklistener.
         */
        buttonThree.setOnClickListener {
            thirdDialog.show()
        }
    }

    /**
     * When asking for permissions for real applications, we should always check what
     * type of version of Android the user is using, or the app may crash.
     *
     * Also, ActivityCompat is used to check permissions, as well as other core Android
     * concepts.
     */


    /*
   Method to write to the external storage of the device.
    */
    private fun hasWriteExternalStoragePermission() =
        ActivityCompat.checkSelfPermission(
            // checks whether a user accepted a permission in the past/currently
            this,
            Manifest.permission.WRITE_EXTERNAL_STORAGE // type of permission
        ) == PackageManager.PERMISSION_GRANTED // returns a result of permission granted

    /*
    Coarse location is used to determine the foreground location of the application.
     */
    private fun hasLocationForegroundPermission() =
        ActivityCompat.checkSelfPermission(
            // checks whether a user accepted a permission in the past/currently
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION // type of permission
        ) == PackageManager.PERMISSION_GRANTED // returns a result of permission granted

    /*
    Fine location is used to determine the background location of the application.
     */
    private fun hasLocationBackgroundPermission() =
        ActivityCompat.checkSelfPermission(
            // checks whether a user accepted a permission in the past/currently
            this,
            Manifest.permission.ACCESS_FINE_LOCATION // type of permission
        ) == PackageManager.PERMISSION_GRANTED // returns a result of permission granted

    /*
    Permission for accessing the internet.
     */
    private fun hasInternetPermission() =
        ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.INTERNET
        ) == PackageManager.PERMISSION_GRANTED

    private fun requestPermissions() {
        val permissionsToRequest = mutableListOf<String>()

        // If user did not accept permissions, then add it to the list.
        if (!hasWriteExternalStoragePermission()) {
            permissionsToRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (!hasLocationForegroundPermission()) {
            permissionsToRequest.add(Manifest.permission.ACCESS_COARSE_LOCATION)
        }
        if (!hasLocationBackgroundPermission()) {
            permissionsToRequest.add(Manifest.permission.ACCESS_FINE_LOCATION)
        }
        if (!hasInternetPermission()) {
            permissionsToRequest.add(Manifest.permission.INTERNET)
        }

        /*
        Now the mutable list has all the permissions that the user has either not accepted,
        or revoked.

        Now we check to see if the list is not empty, if not we want ot request all of
        the permissions inside of the list.
         */
        if (permissionsToRequest.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                permissionsToRequest.toTypedArray(), // returns a typed array of permissions.
                0
            )
        }
    }

    /*
    Method called when the permissions are requested from the user.

    Request code is used when we want to request several permissions at different times.

    Different times = different request codes.
     */
    override fun onRequestPermissionsResult(
        requestCode: Int, // used ot differentiate between several requested permissions.
        permissions: Array<out String>,
        grantResults: IntArray // contains package manager permission granted integers.
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 0 && grantResults.isNotEmpty()) {
            // can loop through grant results array.
            for (i in grantResults.indices) {// indices = size -1
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Log.d(
                        "MainActivity Permissions Request.",
                        "${permissions[i]} granted."
                    ) // print the permissions granted
                }
            }
        }
    }

}