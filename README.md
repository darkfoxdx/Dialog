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

You will need to create an instance of **DialogBuilder** using the constructor. Once the builder is configured, you can 
call **show()** to display the dialog.

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