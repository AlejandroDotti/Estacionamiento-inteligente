package procesoBarrera;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;
public class PlayVideo extends Thread {
	public  EmbeddedMediaPlayerComponent mediaPlayerComponent;
	public PlayVideo(GUI_procesoBarrera frame) {
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                mediaPlayerComponent.release();
                System.exit(0);
            }
        });
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        mediaPlayerComponent.setBounds(355, 25, 290, 198);
        frame.getContentPane().add(mediaPlayerComponent);	 
        frame.setVisible(true);
        mediaPlayerComponent.mediaPlayer().media().play("udp://@231.1.1.1:1234");

	}
}
