# Projecteugene Dialog
Simple Dialog Builder using RxJava and Databinding

This is a simplified Android dialog fragment builder which is easily customizable. Instead of extending the DialogFragment 
class each time, you can set the values using the builder and display it. Features includes handling configurable buttons, 
lists, radio buttons, check boxes, custom XML layout, handlers using RxJava and databinding.

## Set Up

Make sure you have defined the **jcenter()** repository in project's **build.gradle** file:
```
allprojects {
    repositories {
        jcenter()
    }
}
```

Add the dependency to module's **build.gradle** file:
```
dependencies {
    implementation 'com.projecteugene:dialog:1.2'
}
```

Optional - If you want to use the RxJava features:
```
dependencies {
    // RxJava needed for event handlers like button clicks or to react to dialog events
    implementation 'io.reactivex.rxjava2:rxjava:2.2.7'
    // RxKotlin for the convenient extensions to add to composite disposables
    implementation 'io.reactivex.rxjava2:rxkotlin:2.3.0'
}
```

## How To Use

### Basic implementation 

![Basic Dialog](https://i.imgur.com/mbHepyU.png)

You will need to create an instance of ```DialogBuilder``` using the constructor. Once the builder is configured, you can 
call ```show()``` to display the dialog.

```
val builder: DialogBuilder = 
    DialogBuilder(this@MainActivity, supportFragmentManager)
        .setMessage("This is a sample dialog")
builder.show()
```

Alternatively, you can shorten it to:
```
DialogBuilder(this@MainActivity, supportFragmentManager)
    .setMessage("This is a sample dialog")
    .show()
```

If you want to build it without displaying, you can use ```build()``` instead.
```
val fragment: CustomDialogFragment<Nothing> = 
    DialogBuilder(this@MainActivity, supportFragmentManager)
        .setMessage("This is a sample dialog")
        .build()
```

### Style

The default style is used when building the dialog but you can set your own custom style. 

**style.xml**
```
<style name="AlertDialogCustom" parent="Theme.AppCompat.Light.Dialog">
    <item name="android:windowBackground">@null</item>
</style>
```

Then you can customize it in the builder.

```
DialogBuilder(this@MainActivity, supportFragmentManager)
    .setStyle(R.style.AlertDialogCustom)
    .setMessage("This is a sample dialog")
    .show()
```

### Title

By default, title is not set and will not show. You can customize the text for the title if you want.

```
DialogBuilder(this@MainActivity, supportFragmentManager)
    .setTitle("Sample Title")
    .setMessage("This is a sample dialog")
    .show()
```

### Negative, Positive and Neutral buttons

By default, the builder is initialized with ```setTextNegativeButton(android.R.string.ok)```. 

However, if you do not want any buttons to be displayed at all, you can do the following.

```
DialogBuilder(this@MainActivity, supportFragmentManager)
    .setMessage("This is a sample dialog")
    .setTextNegativeButton(null)
    .show()
```

To configure the respective negative, positive and neutral buttons, use the following. Both **Android String ID** and 
**String** values can be used.

```
DialogBuilder(this@SampleActivity, supportFragmentManager)
   .setMessage("This is a sample dialog")
   .setTextNegativeButton("Negative")
   .setTextNeutralButton("Neutral")
   .setTextPositiveButton("Positive")
   .show()
```



