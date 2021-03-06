/**
 * "Dark Triangles" demonstrates black box testing for educational purposes.
 * Copyright (C) 2010-2013 Daniel Speicher and University of Bonn
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package geometry;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import geometry.basic.Point;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class TriangleTest {

	/*
	 * The following methods make sure that the test is run for all Triangle
	 * classes we are interested in. You don't need to understand the mechanics, 
	 * just make sure that you never create a triangle directly, but use one of 
	 * the methods createTriangle(...).
	 */
	private Class<Triangle> classUnderTest;

	@SuppressWarnings("unchecked")
	public TriangleTest(Object classUnderTest) {
		this.classUnderTest = (Class<Triangle>) classUnderTest;
	}

	@Parameters
	public static Collection<Object[]> triangleClassesUnderTest() {
		Object[][] classes = { 
				{ Triangle00.class },
				{ Triangle01.class },
				{ Triangle02.class },
				{ Triangle03.class }, 
				{ Triangle04.class },
				{ Triangle05.class },
				{ Triangle06.class },
				{ Triangle07.class },
				{ Triangle08.class },
				{ Triangle09.class },
				{ Triangle10.class },
				{ Triangle11.class },	
				{ Triangle12.class },
				{ Triangle13.class },
				{ Triangle14.class },
				{ Triangle15.class } ,
				{ Triangle16.class }
		};
		return Arrays.asList(classes);
	}

	private Triangle createTriangle(Point a, Point b, Point c) throws Exception {
		Constructor<Triangle> constructor = classUnderTest.getConstructor(
				Point.class, Point.class, Point.class);
		return constructor.newInstance(a, b, c);
	}

	private Triangle createTriangle(double ax, double ay, double bx, double by,
			double cx, double cy) throws Exception {
		Point a = new Point(ax, ay);
		Point b = new Point(bx, by);
		Point c = new Point(cx, cy);
		return createTriangle(a, b, c);
	}

	/*
	 * Create some triangles for the test.
	 * 
	 * Remember to always use createTriangle(...) to create a triangle. Never
	 * call the constructor directly. This allows us to run the test for all the
	 * classes one after another.
	 */
	private Triangle triangle, inner_triangle, outer_triangle,	partial_inner,partially_outer_triangle;
	private Triangle equalToTriangle;
	private Triangle veryLargeTriangle;
	private Triangle verySmallTriangle;

	@Before
	public void setUpBefore() throws Exception {
		Point a = new Point(0, 0);
		Point b = new Point(0, 2);
		Point c = new Point(2, 0);
		triangle = createTriangle(a, b, c);
		
		inner_triangle = createTriangle(0,0.5,0.5,0,0.5,0.5);
		partially_outer_triangle=createTriangle(0,2,7,2,4,4);
		partial_inner =createTriangle(0,0,0,2,4,4);
		outer_triangle = createTriangle(20,20,90,90,100,100);
		equalToTriangle = createTriangle(c, b, a);
		veryLargeTriangle = createTriangle(-10000.0, -10000.0, +10000.0,
				-10000.0, 0.0, +20000.0);
		verySmallTriangle = createTriangle(-0.00001, -0.00001, +0.00001,
				-0.00001, 0.0, +0.00002);
	}

	@Test
	public void ifATriangleIsComparedWithItself() throws Exception {
		assertTrue("... it should contain itself.", triangle.contains(triangle));
	}
	
	@Test
	public void checkIfTraingleExistsInside() throws Exception {
		assertTrue("... is completely inside", triangle.contains(inner_triangle));
}
	@Test
	public void checkIfTraingleExistsPartiallyOutside() throws Exception {
		assertFalse("... is partially outside", triangle.contains(partially_outer_triangle));
	}
	@Test
	public void checkIfTraingleExistsOutside() throws Exception {
	assertFalse("... is completely outside", triangle.contains(outer_triangle));
	}
	
	@Test
	public void checkIfTraingleExistsPartiallyInside() throws Exception {
	assertTrue("... is partially inside ", triangle.contains(partial_inner));
	}

//	@Test
//	public void ifTrianglesAreEqualToEachOther() throws Exception {
//		assertTrue("... they should contain each other.",
//				triangle.contains(equalToTriangle));
//		assertTrue("The triangles should actually be equal.",
//				triangle.equals(equalToTriangle));
//		assertFalse("But not the same.", triangle == equalToTriangle);
//	}
//
//	@Test
//	public void ifATriangleIsJustACornerOfAnotherTriangle() throws Exception {
//		Point x = new Point(Math.E, Math.E); // Some strange values for this
//											 // simple case.
//		Point y = new Point(Math.PI, Math.E);
//		Point z = new Point(Math.PI, Math.PI);
//		Triangle fullTriangle = createTriangle(x, y, z);
//		Triangle pointX = createTriangle(x, x, x);
//		Triangle pointY = createTriangle(y, y, y);
//		Triangle pointZ = createTriangle(z, z, z);
//		assertTrue("... it is part of the triangle",
//				fullTriangle.contains(pointX));
//		assertTrue("... it is part of the triangle",
//				fullTriangle.contains(pointY));
//		assertTrue("... it is part of the triangle",
//				fullTriangle.contains(pointZ));
//	}

//	@Test @Ignore 
//	public void ifOneTriangleIsVeryLargeAndTheOtherVerySmall() throws Exception {
//		assertTrue("... the large one should contain the small one.",
//				veryLargeTriangle.contains(verySmallTriangle));
//		assertFalse("... the small one should not contain the large one.",
//				verySmallTriangle.contains(veryLargeTriangle));
//	}
	
}
