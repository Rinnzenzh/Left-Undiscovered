package net.rinnzenzh.leftundiscovered.misc;

import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;

public class AllPurposeUtility {

    public static Direction[] horizontal() {
        return new Direction[]{Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};
    }

    public static int randRange(int min, int max, RandomSource random) {
        return min + random.nextInt(max - min + 1);
    }

    public static double randRange(double min, double max, RandomSource random) {
        return min + random.nextDouble() * (max - min);
    }

    public static float randRange(float min, float max, RandomSource random) {
        return min + random.nextFloat() * (max - min);
    }

    public static int min(int a, int b) {
        return a < b ? a : b;
    }

    public static int min(int a, int b, int c) {
        return min(a, min(b, c));
    }

    public static int max(int a, int b) {
        return a > b ? a : b;
    }

    public static float min(float a, float b) {
        return a < b ? a : b;
    }

    public static float max(float a, float b) {
        return a > b ? a : b;
    }

    public static float max(float a, float b, float c) {
        return max(a, max(b, c));
    }

    public static int max(int a, int b, int c) {
        return max(a, max(b, c));
    }

    public static boolean isEven(int num) {
        return (num & 1) == 0;
    }

    public static float lengthSqr(float x, float y, float z) {
        return x * x + y * y + z * z;
    }

    public static double lengthSqr(double x, double y, double z) {
        return x * x + y * y + z * z;
    }

    public static float length(float x, float y, float z) {
        return (float) Math.sqrt(lengthSqr(x, y, z));
    }

    public static double length(double x, double y, double z) {
        return Math.sqrt(lengthSqr(x, y, z));
    }

    public static float lengthSqr(float x, float y) {
        return x * x + y * y;
    }

    public static double lengthSqr(double x, double y) {
        return x * x + y * y;
    }

    public static float length(float x, float y) {
        return (float) Math.sqrt(lengthSqr(x, y));
    }

    public static double length(double x, double y) {
        return Math.sqrt(lengthSqr(x, y));
    }

    public static float dot(float x1, float y1, float z1, float x2, float y2, float z2) {
        return x1 * x2 + y1 * y2 + z1 * z2;
    }

    public static float dot(float x1, float y1, float x2, float y2) {
        return x1 * x2 + y1 * y2;
    }

}
