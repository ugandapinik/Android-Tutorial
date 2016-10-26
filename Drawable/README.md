#Drawables
A drawable resource is a general concept for a graphic that can be drawn to the screen. Drawables are used to define shapes, colors, borders, gradients, etc. which can then be applied to views within an Activity.

##Usage
There are at least 17 types of drawables but there are five that are most important to understand:

<ol>
	<li><b>Shape Drawables</b> - Defines a shape with properties such as stroke, fill, and padding</li>
	<li><b>StateList Drawables</b> - Defines a list of drawables to use for different states</li>
	<li><b>LayerList Drawables</b> - Defines a list of drawables group together into a composite result</li>
	<li><b>NinePatch Drawables</b> - A PNG file with stretchable regions to allow proper resizing</li>
	<li><b>Vector Drawables</b> - Defines complex XML-based vector images</li>
</ol>

Let's explore these drawable file types one by one and take a look at examples of usage.


##Shape Drawable

The Shape Drawable is an XML file that defines a geometric shape, including colors and gradients. 

### Solid Color Shape With Border

create a <b>drawable</b> file named as 'solid_color_shape'

```XML
<?xml version="1.0" encoding="utf-8"?>
<shape
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:shape="rectangle">
    <corners
        android:radius="4dp"/>
    <stroke
        android:width="4dp"
        android:color="#C1E1A6"/>
    <solid
        android:color="#118C4E"/>
    <padding
        android:left="20dp"
        android:top="20dp"
        android:right="20dp"
        android:bottom="20dp" />
</shape>
```

And display the button in the activity_main.xml file by pasting this code.

```XML
<TextView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:background="@drawable/solid_color_shape"
        android:id="@+id/solid_color_shape"
        android:textColor="#FFFFFF"
        android:textAppearance="?android:textAppearanceLarge"
        android:text="Shape Drawable"
        android:layout_marginTop="38dp"
        android:layout_alignParentTop="true"
        android:layout_centerHorizontal="true" />
<!-- SOLID COLOR SHAPE -->
```

    
	
### Gradient Color Shape
### Radial Gradient Color Shape
