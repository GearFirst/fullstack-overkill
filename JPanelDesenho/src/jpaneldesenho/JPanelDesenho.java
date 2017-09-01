package jpaneldesenho;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
public class JPanelDesenho extends JPanel {
    private BufferedImage bufferedImage;//buffer
    public JPanelDesenho() {
        super(true);//bufferstrategy '2', acelera a atualizaçao da imagem
        int width = 800;
        int height = 600;
        bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);// cria um buffer com o tamanho e o tipo definido, outro tipo comum BufferedImage.TYPE_INT_RGB
        addMouseListener(new AcaoMouse());//acao de clike
        addMouseMotionListener(new AcaoMouse());//acao de arrastar (drag)
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE); //seleciona cor de fundo
        g.fillRect(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight());//pinta tudo com a cor selecionada
        g.drawImage(bufferedImage, 0, 0, null); // pinta o jpanel com o buffer
        g.dispose();
    }
    private class AcaoMouse implements MouseListener, MouseMotionListener {
        @Override
        public void mouseClicked(MouseEvent e) {
        }
        @Override
        public void mousePressed(MouseEvent e) {
            if(e.isMetaDown()){
                pintaBranco(e.getX(), e.getY());
            }else{
                  pinta(e.getX(), e.getY());
            }
          
        }
        @Override
        public void mouseReleased(MouseEvent e) {
            if(e.isMetaDown()){
                pintaBranco(e.getX(), e.getY());
            }else{
                  pinta(e.getX(), e.getY());
            }
          
        }
        @Override
        public void mouseEntered(MouseEvent e) {
        }
        @Override
        public void mouseExited(MouseEvent e) {
        }
        @Override
        public void mouseDragged(MouseEvent e) {
            if(e.isMetaDown()){
                pintaBranco(e.getX(), e.getY());
            }else{
                pinta(e.getX(), e.getY());
            }
            
        }
        @Override
        public void mouseMoved(MouseEvent e) {
        }
        
        private void pinta(int x, int y) {
            Graphics gDoBuffer = bufferedImage.createGraphics();//pega o graphics do buffer para edicao
            gDoBuffer.setColor(Color.BLACK); //seta a cor do pincel
            gDoBuffer.fillRect(x, y, 10, 10); //desenha um ponto
            gDoBuffer.dispose();
            updateUI();//atualiza o jpanel, ou seja, "diz ao jpanel q seu desenho foi atualizado e vc qé q seja exibido"
        }
        private void pintaBranco(int x, int y) {
            Graphics gDoBuffer = bufferedImage.createGraphics();//pega o graphics do buffer para edicao
            gDoBuffer.setColor(Color.WHITE); //seta a cor do pincel
            gDoBuffer.fillRect(x, y, 50, 50); //desenha um ponto
            gDoBuffer.dispose();
            updateUI();//atualiza o jpanel, ou seja, "diz ao jpanel q seu desenho foi atualizado e vc qé q seja exibido"
        }
    }
    public static void main(String[] args) {
        //Teste
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanelDesenho desenho = new JPanelDesenho();
        desenho.setPreferredSize(new Dimension(800, 600));
        jFrame.getContentPane().add(desenho);
        jFrame.setResizable(false);
        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setLocationRelativeTo(null);
    }
}
