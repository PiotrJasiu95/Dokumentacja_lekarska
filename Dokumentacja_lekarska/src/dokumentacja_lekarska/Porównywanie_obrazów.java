/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dokumentacja_lekarska;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Piotr
 */
public class Porównywanie_obrazów {

    private String fileNameObraz1;
    private String fileNameObraz2;
    private BufferedImage pierwszy = null;
    private BufferedImage drugi = null;
    private BufferedImage trzeci;
    private double tolerancja = 0;
    private double roznica = 0;
    private boolean kolrowanie = false;
    private int x = 0;
    private int y = 0;

    public Porównywanie_obrazów(String fileNameObraz1, String fileNameObraz2, boolean kolorowanie, double tolerancja) {
        this.fileNameObraz1 = fileNameObraz1;
        this.fileNameObraz2 = fileNameObraz2;
        this.kolrowanie = kolorowanie;
        this.tolerancja = tolerancja;
    }

    public boolean porównajObrazy() {
        try {
            pierwszy = ImageIO.read(new File(fileNameObraz1));
            drugi = ImageIO.read(new File(fileNameObraz2));
            trzeci = ImageIO.read(new File(fileNameObraz1));

        } catch (IOException e) {
            System.out.println("błąd wczytania obrazów");
        }
        int f = 20;
        int w1 = Math.min(50, pierwszy.getWidth() - drugi.getWidth());
        int h1 = Math.min(50, pierwszy.getHeight() - drugi.getHeight());
        int w2 = Math.min(5, pierwszy.getWidth() - drugi.getWidth());
        int h2 = Math.min(5, pierwszy.getHeight() - drugi.getHeight());
        for (int i = 0; i <= pierwszy.getWidth() - drugi.getWidth(); i += f) {
            for (int j = 0; j <= pierwszy.getHeight() - drugi.getHeight(); j += f) {
                porównajPixele(i, j, f);
                if (kolrowanie == true) {
                    zmienKolorPixeli(i, j, f);
                }
            }
        }

        pierwszy = pierwszy.getSubimage(Math.max(0, x - w1), Math.max(0, y - h1),
                Math.min(drugi.getWidth() + w1, pierwszy.getWidth() - x + w1),
                Math.min(drugi.getHeight() + h1, pierwszy.getHeight() - y + h1));
        x = 0;
        y = 0;
        roznica = 0;
        f = 5;
        for (int i = 0; i <= pierwszy.getWidth() - drugi.getWidth(); i += f) {
            for (int j = 0; j <= pierwszy.getHeight() - drugi.getHeight(); j += f) {
                porównajPixele(i, j, f);
                if (kolrowanie == true) {
                    zmienKolorPixeli(i, j, f);
                }
            }
        }
        pierwszy = pierwszy.getSubimage(Math.max(0, x - w2), Math.max(0, y - h2),
                Math.min(drugi.getWidth() + w2, pierwszy.getWidth() - x + w2),
                Math.min(drugi.getHeight() + h2, pierwszy.getHeight() - y + h2));
        f = 1;
        for (int i = 0; i <= pierwszy.getWidth() - drugi.getWidth(); i += f) {
            for (int j = 0; j <= pierwszy.getHeight() - drugi.getHeight(); j += f) {
                porównajPixele(i, j, f);
                if (kolrowanie == true) {
                    zmienKolorPixeli(i, j, f);
                }
            }
        }
        System.out.println(roznica);
        return roznica < 0.1;
    }

    public void porównajPixele(int a, int b, int f) {
        double diff = 0;
        for (int i = 0; i < drugi.getWidth(); i += f) {
            for (int j = 0; j < drugi.getHeight(); j += f) {
                int onepx = pierwszy.getRGB(i + a, j + b);
                int twopx = drugi.getRGB(i, j);
                int r1 = (onepx >> 16);
                int g1 = (onepx >> 8) & 0xff;
                int b1 = (onepx) & 0xff;
                int r2 = (twopx >> 16);
                int g2 = (twopx >> 8) & 0xff;
                int b2 = (twopx) & 0xff;
                diff += (Math.abs(r1 - r2) + Math.abs(g1 - g2) + Math.abs(b1
                        - b2)) / 3.0 / 255.0;
            }
        }
        double percentDiff = diff * f * f / (drugi.getWidth() * drugi.getHeight());
        if (percentDiff < roznica || roznica == 0) {
            roznica = percentDiff;
            x = a;
            y = b;
        }
    }

    public void zmienKolorPixeli(int a, int b, int f) {
        double currentDiff = 0;
        for (int i = 0; i < drugi.getWidth(); i += f) {
            for (int j = 0; j < drugi.getHeight(); j += f) {
                int onepx = pierwszy.getRGB(i + a, j + b);
                int twopx = drugi.getRGB(i, j);
                int r1 = (onepx >> 16);
                int g1 = (onepx >> 8) & 0xff;
                int b1 = (onepx) & 0xff;
                int r2 = (twopx >> 16);
                int g2 = (twopx >> 8) & 0xff;
                int b2 = (twopx) & 0xff;
                currentDiff = Math.abs(r1 - r2) + Math.abs(g1 - g2) + Math.abs(b1 - b2) / 3.0 / 255.0;
                if (currentDiff > 0) {
                    r1 = (int) (r1 + ((currentDiff * 255.0) / 3.0 / tolerancja));
                }
                if (r1 > 255) {
                    r1 = 255;
                }
                int treepx = 0;
                treepx = treepx | (r1 << 16);
                treepx = treepx | (g1 << 8);
                treepx = treepx | b1;
                trzeci.setRGB(i, j, treepx);
            }
        }
    }

    public BufferedImage getPierwszyObraz() {
        return pierwszy;
    }

    public void setPierwszyObraz(BufferedImage pierwszy) {
        this.pierwszy = pierwszy;
    }

    public BufferedImage getDrugiObraz() {
        return drugi;
    }

    public void setDrugi(BufferedImage pierwszy) {
        this.drugi = pierwszy;
    }

    public BufferedImage getTrzeciObraz() {
        return trzeci;
    }

    public double getRoznica() {
        return roznica;
    }

}
