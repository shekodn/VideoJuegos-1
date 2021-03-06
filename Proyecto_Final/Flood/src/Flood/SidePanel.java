package Flood;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JPanel;

/**
 *
 * @author Left & Right
 * @version 1.0
 * @date APR/12/2016
 */
public class SidePanel extends JPanel implements MouseListener {

    //Variable que indica si el mouse intersecta con algún botón
    private boolean bIntersects;

    //Objetos Base para los botones del panel

    /**
     *
     */
    protected Base basBackMenu;
    private Base basHelp;

    /**
     *
     */
    protected Base basPause;

    /**
     *
     */
    protected Base basSound;

    /**
     *
     */
    protected Base basYesSalir;//salirdeljuego

    /**
     *
     */
    protected Base basNoPlay;//seguir jugando

    //Objetos para las imagenes
    private Image imaImagenLogo;
    private Image imaImagenNivel;
    private Image imaImagenBannerSalir;
    private Image imaImagenPausa;
    private Image imaImagenLevelUp;
    private Image imaImagenLevelDown;
    private Image imaImagenWonGame;

    //instrucciones
    private Image imaInst1;
    private Image imaInst2;
    private Image imaInst3;
    private Image imaInst4;
    private Image imaInst5;
    private Image imaInst6;




    //Variables booleanas que indican si un botón fue presionado
    private boolean bHelp;

    /**
     *
     */
    protected boolean bPause;

    /**
     *
     */
    protected boolean bSound;

    /**
     *
     */
    protected boolean bExit;

    /**
     *
     */
    protected boolean bBanner;

    /**
     *
     */
    protected boolean bLevelUp;
    protected boolean bLevelDown;
    /**
     *
     */
    protected boolean bWonGame;

    //contadores

    /**
     *
     */
    protected int iContBannerLevel;

    //Variables que indican los tamaños del side panel
    private int iStartPanelX;

    //Variables de offsets para botones del panel
    private int iXOffsetSelections;

    //Variables de posicion del mouse
    int iMouseX;
    int iMouseY;
    int iMouseXOffSet;
    int iMouseYOffSet;

    //Variable que contiene el #de nivel como string,se usa para pintar el nivel
    private String sNivel;

    //The Flood Game instance
    private Flood tarGame;

    //Font a usar
    private Font fonFuentel;

    //Banner Menu
    private BannerMenu bannerMenu;

    //Offset del side panel
    int iOffsetXimagen;
    int iOffsetYimagen;

    /**
     * SidePanel
     * @param floodGame
     * @param Flood
     * Constructor de SidePanel, inicializa
     * @throws java.awt.FontFormatException
     * @throws java.io.IOException
     */
    public SidePanel(Flood floodGame) throws FontFormatException, IOException {

        this.tarGame = floodGame;//instancia de flood
        initVars();
        creaBases();
        crearImagenes();
        addMouseListener(this);

        //inicializa fonts
        InputStream fontStream = getClass().getResourceAsStream
                                 ("/Flood/Score.ttf");
        this.fonFuentel = Font.createFont(Font.TRUETYPE_FONT, fontStream);
        this.fonFuentel = this.fonFuentel.deriveFont(40F);

        setPreferredSize(new Dimension((tarGame.iWidth) -
                                       ((tarGame.iWidth) - 289), tarGame.iHeight));

    }

    /**
     * initvars
     *
     * inicializa las variables
     */
    public void initVars() {
        //booleanas de botones
        bHelp = false;
        bPause = false;
        bSound = true;
        bExit = false;
        //booleanas de banners y mensajes
        bLevelDown = false;
        bLevelUp = false;
        bBanner = false;
        bWonGame = false;

        //indica el nivel
        sNivel = Integer.toString(tarGame.iNivel);

        //offsets
        iStartPanelX = (tarGame.iWidth) - 289;
        iMouseXOffSet = iStartPanelX;
        iMouseYOffSet = 20;
        iXOffsetSelections = 3;

        //contador para desplegar los banners, cuando escuchar el teclado
        iContBannerLevel = 5;
    }

    /**
     * crearImagenes
     * crea las imagenes base del side panel
     */
    public void crearImagenes() {
        // Crear la imagen de fondo.
        imaImagenLogo = Toolkit.getDefaultToolkit().getImage(this.getClass()
                        .getResource("Images/sidePanel/FloodPuntos.png"));

        sNivel = "1";
        imaImagenNivel = Toolkit.getDefaultToolkit().getImage(this.getClass()
                         .getResource("Images/sidePanel/nivel" + sNivel + ".png"));

        imaImagenBannerSalir = Toolkit.getDefaultToolkit().getImage(this.
                               getClass().getResource("Images/sidePanel/bannerSalir.png"));

        imaImagenPausa = Toolkit.getDefaultToolkit().getImage(this.getClass()
                         .getResource("Images/sidePanel/bannerPausa.png"));

        imaImagenLevelUp = Toolkit.getDefaultToolkit().getImage(this.getClass()
                           .getResource("Images/sidePanel/levelUp.png"));

        imaImagenLevelDown = Toolkit.getDefaultToolkit().getImage(this.getClass()
                             .getResource("Images/sidePanel/ResetLevel.png"));

        imaImagenWonGame = Toolkit.getDefaultToolkit().getImage(this.getClass()
                           .getResource("Images/sidePanel/YouWon.png"));

        //imagen de instrucciones

        imaInst1 = Toolkit.getDefaultToolkit().getImage(this.getClass()
                   .getResource("Images/sidePanel/Instrucciones/1.png"));

        imaInst2 = Toolkit.getDefaultToolkit().getImage(this.getClass()
                   .getResource("Images/sidePanel/Instrucciones/2.png"));

        imaInst3 = Toolkit.getDefaultToolkit().getImage(this.getClass()
                   .getResource("Images/sidePanel/Instrucciones/3.png"));

        imaInst4 = Toolkit.getDefaultToolkit().getImage(this.getClass()
                   .getResource("Images/sidePanel/Instrucciones/4.png"));

        imaInst5 = Toolkit.getDefaultToolkit().getImage(this.getClass()
                   .getResource("Images/sidePanel/Instrucciones/5.png"));

        imaInst6 = Toolkit.getDefaultToolkit().getImage(this.getClass()
                   .getResource("Images/sidePanel/Instrucciones/6.png"));
    }

    /** creaBases
     * crea los objetos base del side panel, botones
     */
    public void creaBases() {

        basBackMenu = new Base(630, 570, Toolkit.getDefaultToolkit()
                               .getImage(this.getClass().getResource
                                         ("Images/sidePanel/backMenu.png")));

        basPause = new Base(iStartPanelX, 662, Toolkit.getDefaultToolkit()
                            .getImage(this.getClass().getResource
                                      ("Images/sidePanel/Pause.png")));

        basSound = new Base(iStartPanelX, 662, Toolkit.getDefaultToolkit()
                            .getImage(this.getClass().getResource
                                      ("Images/sidePanel/Sound.png")));

        basHelp = new Base(iStartPanelX, 662, Toolkit.getDefaultToolkit()
                           .getImage(this.getClass().getResource
                                     ("Images/sidePanel/Question.png")));

        basPause.setX(iStartPanelX + 119 - (basPause.getAncho() / 2));

        basSound.setX(basPause.getX() - basSound.getAncho() -
                      iXOffsetSelections);

        basHelp.setX(basPause.getX() + basPause.getAncho() +
                     iXOffsetSelections);

        basYesSalir = new Base(255, 400, Toolkit.getDefaultToolkit()
                               .getImage(this.getClass().
                                         getResource("Images/sidePanel/yes.png")));

        basNoPlay = new Base(basYesSalir.getX() + basYesSalir.getAncho() + 50,
                             400, Toolkit.getDefaultToolkit().getImage(this.getClass().
                                     getResource("Images/sidePanel/no.png")));

    }

    /***
     * paint component
     * @param graGrafico
     */
    public void paintComponent(Graphics graGrafico) {

        //iOffsetXimagen = iStartPanelX + (119 - (imaImagenLogo.getWidth(this)/2));
        iOffsetXimagen = 630;
        iOffsetYimagen = 40;
        //super.paintComponent(graGrafico);
        /*pinta las imagenes*/



        if (imaImagenLogo != null && imaImagenNivel != null) {
            //pinta imagenes
            graGrafico.drawImage(imaImagenLogo, iOffsetXimagen, iOffsetYimagen,
                                 196, 193, this);
            //poner color y font
            Color colAux = new Color(255, 255, 255);
            graGrafico.setColor(colAux);
            graGrafico.setFont(fonFuentel);

            Rectangle rect = new Rectangle(669, 195, 127, 34);

            //centra los puntos en el recuadro
            fitInSquare(Integer.toString(tarGame.iPuntos), rect, graGrafico);
            //graGrafico.drawString(Integer.toString(tarGame.iPuntos), 710, 213);

            graGrafico.drawImage(imaImagenNivel, iOffsetXimagen, iOffsetYimagen
                                 + 212, imaImagenNivel.getWidth(this),
                                 imaImagenNivel.getHeight(this), this);



            dibujaInstrucciones(graGrafico);



        } else {
            //Da un mensaje mientras se carga el dibujo
            graGrafico.drawString("No se cargo la imagen..", 20, 20);
        }


        /*pinta los objetos*/
        if (basBackMenu != null
                && basHelp != null && basPause != null && basSound != null) {
            // Dibujar el objeto de menu
            basBackMenu.paint(graGrafico, this);
            basHelp.paint(graGrafico, this);
            basPause.paint(graGrafico, this);
            basSound.paint(graGrafico, this);

        } else {
            //Da un mensaje mientras se carga el dibujo
            graGrafico.drawString("No se cargo la imagen..", 20, 20);
        }

        if (bPause && !bBanner) {//el juego esta en pausa
            //pinta banner de fondo
            graGrafico.drawImage(imaImagenPausa, -8, -10, imaImagenPausa.
                                 getWidth(this), imaImagenPausa.getHeight(this), this);
        }

        if (bExit) {//el usuario quiere regresar a menu
            //pinta banner de fondo
            graGrafico.drawImage(imaImagenBannerSalir, 0, 0, tarGame.iWidth,
                                 tarGame.iHeight, this);
            //pinta los botones/objetos
            basYesSalir.paint(graGrafico, this);
            basNoPlay.paint(graGrafico, this);
        }

        if (bLevelUp) {//el juego subio de nivel


            //banner cambio de nivel
            graGrafico.drawImage(imaImagenLevelUp, 0, 0, imaImagenPausa.
                                 getWidth(this), imaImagenPausa.getHeight(this), this);

        }

        if (bLevelDown) {//no se paso el nivel
            //banner cambio de nivel
            graGrafico.drawImage(imaImagenLevelDown, 0, 0, imaImagenPausa.
                                 getWidth(this), imaImagenPausa.getHeight(this), this);
        }

        if (bWonGame) {
            graGrafico.drawImage(imaImagenWonGame, 0, 0, 900, 788, this);
        }


    }

    /***
     * manejaSonido
     * maneja el sonido del juego
     */
    public void manejaSonido() {
        if (bSound) {
            //cambio de imagen
            basSound.setImagen(Toolkit.getDefaultToolkit()
                               .getImage(this.getClass().getResource
                                         ("Images/sidePanel/Sound.png")));
            //AQUI PONER EL SOUND.PLAY()
            


        } else {
            //cambio de imagen
            basSound.setImagen(Toolkit.getDefaultToolkit()
                               .getImage(this.getClass().
                                         getResource("Images/sidePanel/Mute.png")));
            //AQUI PONER EL SOUND.PAUSE()
        }
    }

    /**
     * manejaPausa
     * maneja la pausa del juego
     */
    public void manejaPausa() {
        if (bPause) {//esta en pausa
            //cambio de imagen
            basPause.setImagen(Toolkit.getDefaultToolkit()
                               .getImage(this.getClass().
                                         getResource("Images/sidePanel/Play.png")));
        } else {//no esta en pausa
            //cambio de imagen
            basPause.setImagen(Toolkit.getDefaultToolkit()
                               .getImage(this.getClass().
                                         getResource("Images/sidePanel/Pause.png")));
        }
    }

    /***
     * cambioNivel
     * maneja los cambios de nivel (banners y checa si ya termino el juego)
     */
    public void cambioNivel() {
        //indica el nivel
        sNivel = Integer.toString(tarGame.iNivel);

        if (tarGame.iNivel > 1 && tarGame.iNivel <= 6) {
            iContBannerLevel = 10;
            bPause = true;//pone pausa pq despliega un banner
            bLevelUp = true;//prende booleana
            bLevelDown = false;
            bBanner = true;//hay un banner de nivel
        } else if (tarGame.iNivel == 7) {//ya termino el juego
            iContBannerLevel = 10;
            bPause = true;//pone pausa pq despliega un banner
            bWonGame = true;//prende booleana
            bBanner = true;//hay un banner de nivel
        }

        //cambiar imagen a desplegar
        imaImagenNivel = Toolkit.getDefaultToolkit().getImage(this.getClass()
                         .getResource("Images/sidePanel/nivel" + sNivel + ".png"));
    }

    public void perdioNivel() {
        if (tarGame.iNivel >= 1 && tarGame.iNivel <= 6) {
            iContBannerLevel = 10;
            bPause = true;//pone pausa pq despliega un banner
            bLevelUp = false;//prende booleana
            bLevelDown = true;
            bBanner = true;//hay un banner de nivel
        }
    }

    /***
     * getHelp
     * metodo que regresa el atributo de help
     * @return boolean bHelp
     */
    public boolean getHelp() {
        return bHelp;
    }

    /***
     * setHeklp
     * @param help
     * @param boolean help
     * pone un valor booleano al atributo help
     */
    public void setHelp(boolean help) {
        this.bHelp = help;
    }

    /***
     * mouseClicked
     * @param mouEvent
     * realiza los cambios y acciones cuando el usuario utiliza el mouse
     */
    public void mouseClicked(MouseEvent mouEvent) {

        System.out.println("CLICK");

        //actualiza posiciones del mouse
        iMouseX = mouEvent.getX() + iMouseXOffSet;
        iMouseY = mouEvent.getY();

        //checa si el jugador ha presionado algun boton del panel
        if (basHelp.intersects(iMouseX, iMouseY) && !bBanner) {
            bHelp = true;//prende help
            System.out.println("clicked help");
            tarGame.bannerMenu.setInstrucciones(true);//despliega sinstrucciones
            tarGame.bannerMenu.setSecundario(true);//despliega menu secundario
        }
        if (basPause.intersects(iMouseX, iMouseY) && !bBanner) {
            System.out.println("clicked pause");
            bPause = !bPause;//niega pause
            manejaPausa();
        }
        if (basSound.intersects(iMouseX, iMouseY) && !bBanner) {
            System.out.println("clicked sound");
            bSound = !bSound;//niega sound
            manejaSonido();
        }
        if (basBackMenu.intersects(iMouseX, iMouseY) && !bBanner) {

            /*quieres salir del juego?*/
            //pausa juego para que no aparezcan cuadros
            bPause = true;//pone pausa, si es que no esta puesta
            //prende booleana para desplegar mensaje de "seguro?"
            bExit = true;
            bBanner = true;

            System.out.println("clicked menu");
        }
        if (basNoPlay.intersects(iMouseX, iMouseY) && bBanner) {//seguir jugando
            bExit = false;//quitar banner
            bPause = false;//quitar pausa
            bBanner = false;//significa que no hay banner
        }
    }

    /***
     * fitInSquare
     * @param sQuestion
     * @param rect
     * @param graGrafico
     * metodo que centra la pregunta en el cuadro
     */
    public void fitInSquare(String sQuestion, Rectangle rect, Graphics
                            graGrafico) {
        //inicializa valores
        int iStringWidth = graGrafico.getFontMetrics().stringWidth(sQuestion);
        int iStringHeight = graGrafico.getFontMetrics().getAscent();
        //crea los offsets
        int iOffsetX = (int) rect.getX() + (int) (rect.getWidth() / 2) -
                       (iStringWidth / 2);
        int iOffsetY = (int) rect.getY() + (int) (rect.getHeight() / 2) -
                       (iStringHeight / 2) + 20;

        //pinta las preguntas ya con el offset y font
        Color colAux = new Color(255, 255, 255);
        graGrafico.setColor(colAux);
        graGrafico.setFont(fonFuentel);
        graGrafico.drawString(sQuestion, iOffsetX, iOffsetY);
    }

    void dibujaInstrucciones(Graphics graGrafico) {

        if (tarGame.iNivel == 1) {

            graGrafico.drawImage(imaInst1, 642, 334, 172, 169, this);

        }

        if (tarGame.iNivel == 2) {

            graGrafico.drawImage(imaInst2, 642, 334, 172, 169, this);

        }

        if (tarGame.iNivel == 3) {

            graGrafico.drawImage(imaInst3, 642, 350, 172, 169, this);

        }

        if (tarGame.iNivel == 4) {

            graGrafico.drawImage(imaInst4, 642, 334, 172, 169, this);

        }

        if (tarGame.iNivel == 5) {

            graGrafico.drawImage(imaInst5, 642, 334, 172, 169, this);

        }

        if (tarGame.iNivel == 6) {

            graGrafico.drawImage(imaInst6, 642, 334, 172, 169, this);

        }

    }



    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

}
