import edu.princeton.cs.algs4.Picture;

import java.awt.*;
import java.util.Arrays;

public class SeamCarver {
    private Picture picture;
    int width;
    int height;
    private double[][] energy;
    private boolean checkInBound(int x, int y) {
        return x >= 0 && x <= width - 1 && y >= 0 && y <= height - 1;
    }
    private void culEnergy() {
        for (int col = 0; col < width; col += 1) {
            for (int row = 0; row < height; row += 1) {
                double ex;
                double ey;
                Color xtop;
                Color xdown;
                Color yleft;
                Color yright;
                xtop = picture.get(col, (row + height - 1) % height);
                xdown = picture.get(col, (row + 1) % height);
                yleft = picture.get((col + width - 1) % width, row);
                yright = picture.get((col + 1) % width, row);
                ex = Math.pow(xtop.getRed() - xdown.getRed(), 2) + Math.pow(xtop.getGreen() - xdown.getGreen(), 2) + Math.pow(xtop.getBlue() - xdown.getBlue(), 2);
                ey = Math.pow(yleft.getRed() - yright.getRed(), 2) + Math.pow(yleft.getGreen() - yright.getGreen(), 2) + Math.pow(yleft.getBlue() - yright.getBlue(), 2);
                energy[row][col] = ex + ey;
            }
        }
    }
    public SeamCarver(Picture picture) {
        this.picture = picture;
        this.height = picture.height();
        this.width = picture.width();
        energy = new double[height][width];
        culEnergy();
    }

    public Picture picture() {
        return picture;
    }
    public int width() {
        return width;
    }
    public int height() {
        return height;
    }
    public  double energy(int x, int y) {
        if (!checkInBound(x, y)) {
            throw new IndexOutOfBoundsException("x and y should in bound");
        }
        return energy[y][x];
        // energy of pixel at column x and row y
    }
    private double[][] M;
    private int findLeftMin(int i) {
        if (M[i - 1][0] > M[i - 1][1]) {
            return 1;
        } else {
            return 0;
        }
    }
    private int findRightMin(int i, int j) {
        if (M[i - 1][j - 1] > M[i - 1][j]) {
            return j;
        } else {
            return j - 1;
        }
    }
    private int findMiddleMin(int i, int j) {
        double min = M[i - 1][j - 1];
        int res;
        res = j - 1;
        for (int k = j; k < j + 2; k += 1) {
            if (M[i - 1][k] < min) {
                min = M[i - 1][k];
                res = k;
            }
        }
        return res;
    }
    private int[] findVerticalSeamHelp(double[][] energy) {
        int height = energy.length;
        int width = energy[0].length;
        int[] seam = new int[height];
        M = new double[height][width];
        for (int i = 0; i < height; i += 1) {
            for (int j = 0; j < width; j += 1) {
                if (i == 0) {
                    M[i][j] = energy[i][j];
                } else {
                    if (j == 0) {
                        M[i][j] = M[i - 1][findLeftMin(i)] + energy[i][j];
                    } else if (j == width - 1) {
                        M[i][j] = M[i - 1][findRightMin(i ,j)] + energy[i][j];
                    } else {
                        M[i][j] = M[i - 1][findMiddleMin(i, j)] + energy[i][j];
                    }
                }
            }
        }
        //System.out.println(Arrays.deepToString(M));
        double min = M[height - 1][0];
        int col = 0;
        for (int i = 1; i < width; i += 1) {
            if (M[height - 1][i] < min) {
                min = M[height - 1][i];
                col = i;
            }
        }
        int signal = seam.length - 1;
        seam[signal] = col;
        while (signal > 0) {
            if (col == 0) {
                col = findLeftMin(signal);
            } else if (col == width - 1) {
                col = findRightMin(signal, width - 1);
            } else {
                col = findMiddleMin(signal, col);
            }
            signal -= 1;
            seam[signal] = col;
        }
        return seam;
    }
    public int[] findHorizontalSeam() {
        // sequence of indices for horizontal seam
        double[][] reverseEnergy = new double[width][height];
        for (int i = 0; i < width; i += 1) {
            for (int j = 0; j < height; j += 1) {
                reverseEnergy[i][j] = energy[j][i];
            }
        }
        return findVerticalSeamHelp(reverseEnergy);
    }
    public int[] findVerticalSeam() {
        // sequence of indices for vertical seam
        return findVerticalSeamHelp(energy);
    }
    public void removeHorizontalSeam(int[] seam) {
        picture = SeamRemover.removeHorizontalSeam(picture, seam);
        height -= 1;
    }
    public void removeVerticalSeam(int[] seam) {
        // remove vertical seam from picture
        picture = SeamRemover.removeVerticalSeam(picture, seam);
        width -= 1;
    }
}
