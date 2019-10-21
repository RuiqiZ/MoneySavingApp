# Indentation

* Use **4 spaces** for each level of indentation

# Line Length

* By default, Android Studio's editor view displays a vertical line of 101 characters. Let's use this as a guideline.

# Comments
    
* All methods should be commented above their signatures
    * With exception to **setters** and **getters**
* Comments should be written to describe the **current** use of the method
* Method comments should not include terms such as `This method ...`. 
    * Write comments as if you are specifying what the method should do.
* **Note:** Including parameter specific comments are optional

```Java
/** MethodName
* 
* Returns the computed mass of our sun.
*
* @param radius Represents the earths radius... (THIS IS OPTIONAL)
*/
```

* Comments inside method bodies should be written commented using only `single line comments`

```Java
public void doStuff(...) {
    // We will need to determine how far away the sun is before
    // any real work can be done.... REST OF HELPFUL COMMENT HERE
    blah blah blah;
}
```

* Comments inside of method bodies are used only to describe tricky peieces of code. 

# Naming Conventions

* `variables` and `methods` use **camelCase** (stating off with **lowercase**)
* `classes` and `interfaces` USE **CamelCase** (starting off with **capital**)

* `constants` are all **UPPERCASE** with words seperated by **underscores** 

```Java
private final float GRAVITY_CONSTANT = 9.8;
private int myAge = 100;

public static int computeEarthsAge(...) { ... }

public class SunCalculator { ... }
```

# Booleans

* Booleans are always **atleast 2 words**. The first being a verb like **is** or **has** or something similar

```Java
isFound vs found
hasValue vs value
```

# strings.xml

* All values found in `string.xml` should be **lowercase** and words should be seperated by **underscores** 
    * This is an android convension

```Java
the_string
```

* **ALL** text found in our application (with exception to user specified notes) should be found inside of the `strings.xml`
    * This is an android convension

# { Braces }

* The **opening brace** is always on the **same line** as the method's signature, the class name, or the conditional statement
* the **closing brace** is on its *own line*, with the exception of simple `accessor` and `mutators`

```Java
public void computeSunMass(int earthRadius) {
    // Do work here

    if (earthRadius == 0) {
        // Do conditional work here
    }
}
```

# Method signatures

* Method signatures should only be a space **before** the opening brace

```Java
public int add(int x, int y) {
    return x + y;
}
```

* If there is **overloading**, new parameters are added to the **end** of the signature

```Java
public void doSomething(int x) {
    /// Do something with x
}

public void doSomething(int x, int y) {
    // Do something with both x and y
}
```

# Exception handling/logging

* Exceptions just plain suck.
* Lets aim at failing gracefully. If any exception is thrown **print the stack trace**

# Loops and conditional statements

* Braces should be used around **all statements**, even single statements
    * It makes everything a little easier to read

* **Note:** The one exception is with using the **ternary operator**

```Java
return (condition ? x : y);
```

# Package and import statements

* First **non-commented line** in the file should be a package statement, after **import statements** can follow

```Java
package java.awt

import java.blah.blah.module;
```

# File Structure

* Android's guidelines can be found [here](https://developer.android.com/guide/topics/resources/providing-resources)