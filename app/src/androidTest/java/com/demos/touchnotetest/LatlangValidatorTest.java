package com.demos.touchnotetest;

import androidx.test.runner.AndroidJUnit4;

import com.demos.touchnotetest.utils.Utils;

import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@SuppressWarnings("deprecation")
@RunWith(AndroidJUnit4.class)
public class LatlangValidatorTest {

    @Test
    public void validate_Coordinates_ReturnsTrue() {

        assertTrue(new Utils().validateCoordinates(-10.734, -20.000));
    }

    @Test
    public void validate_Coordinates_ReturnsTrue_1() {

        assertTrue(new Utils().validateCoordinates(45, 180));
    }

    @Test
    public void validate_Coordinates_ReturnsTrue_2() {

        assertTrue(new Utils().validateCoordinates(-90.000, -180.000));
    }

    @Test
    public void validate_Coordinates_ReturnsTrue_3() {

        assertTrue(new Utils().validateCoordinates(47.1231231, 179.99999999));
    }

    @Test
    public void validate_Coordinates_ReturnsFalse() {

        assertFalse(new Utils().validateCoordinates(-290.98, 350.90));
    }

    @Test
    public void validate_Coordinates_ReturnsFalse_1() {

        assertFalse(new Utils().validateCoordinates(+450, -180));
    }

    @Test
    public void validate_Coordinates_ReturnsFalse_2() {

        assertFalse(new Utils().validateCoordinates(-0450, 180));
    }

    @Test
    public void validate_Coordinates_ReturnsFalse_3() {

        assertFalse(new Utils().validateCoordinates(47.1231231, 279.99999999));
    }


}
