package org.synapse.oauth.captcha;

import java.awt.*;
import java.io.OutputStream;

public abstract class Captcha extends Randoms {
    public static final int TYPE_DEFAULT = 1;
    public static final int TYPE_ONLY_NUMBER = 2;
    public static final int TYPE_ONLY_CHAR = 3;
    public static final int TYPE_ONLY_UPPER = 4;
    public static final int TYPE_ONLY_LOWER = 5;
    public static final int TYPE_NUM_AND_UPPER = 6;
    public static final int[][] COLOR = {{0, 135, 255}, {51, 153, 51}, {255, 102, 102}, {255, 153, 0}, {153, 102, 0}, {153, 102, 153}, {51, 153, 153}, {102, 102, 255}, {0, 102, 204}, {204, 51, 51}, {0, 153, 204}, {0, 51, 102}};
    protected Font font = new Font("Arial", Font.BOLD, 32);
    protected int len = 4;
    protected int width = 130;
    protected int height = 48;
    protected String chars = null;
    protected int charType = TYPE_DEFAULT;

    protected char[] alphas() {
        char[] cs = new char[len];
        for (int i = 0; i < len; i++) {
            switch (charType) {
                case 2:
                    cs[i] = alpha(numMaxIndex);
                    break;
                case 3:
                    cs[i] = alpha(charMinIndex, charMaxIndex);
                    break;
                case 4:
                    cs[i] = alpha(upperMinIndex, upperMaxIndex);
                    break;
                case 5:
                    cs[i] = alpha(lowerMinIndex, lowerMaxIndex);
                    break;
                case 6:
                    cs[i] = alpha(upperMaxIndex);
                    break;
                default:
                    cs[i] = alpha();
            }
        }
        chars = new String(cs);
        return cs;
    }

    protected Color color(int fc, int bc) {
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + num(bc - fc);
        int g = fc + num(bc - fc);
        int b = fc + num(bc - fc);
        return new Color(r, g, b);
    }

    protected Color color() {
        int[] color = COLOR[num(COLOR.length)];
        return new Color(color[0], color[1], color[2]);
    }

    public abstract boolean out(OutputStream os);

    public String text() {
        checkAlpha();
        return chars;
    }

    public char[] textChar() {
        checkAlpha();
        return chars.toCharArray();
    }

    public void checkAlpha() {
        if (chars == null) {
            alphas();
        }
    }

    public void drawLine(int num, Graphics2D g) {
        drawLine(num, null, g);
    }

    public void drawLine(int num, Color color, Graphics2D g) {
        for (int i = 0; i < num; i++) {
            g.setColor(color == null ? color(150, 250) : color);
            int x1 = num(-10, width - 10);
            int y1 = num(5, height - 5);
            int x2 = num(10, width + 10);
            int y2 = num(2, height - 2);
            g.drawLine(x1, y1, x2, y2);
        }
    }

    public void drawOval(int num, Graphics2D g) {
        for (int i = 0; i < num; i++) {
            g.setColor(color(100, 250));
            g.drawOval(num(width), num(height), 10 + num(20), 10 + num(20));
        }
    }

    public void drawOval(int num, Color color, Graphics2D g) {
        for (int i = 0; i < num; i++) {
            g.setColor(color == null ? color(100, 250) : color);
            g.drawOval(num(width), num(height), 10 + num(20), 10 + num(20));
        }
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getCharType() {
        return charType;
    }

    public void setCharType(int charType) {
        this.charType = charType;
    }
}
