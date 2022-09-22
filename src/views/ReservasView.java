package views;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;
import db.reserva;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.Toolkit;
import java.beans.PropertyChangeListener;
import java.sql.SQLException;
import java.beans.PropertyChangeEvent;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
@SuppressWarnings("serial")
public class ReservasView extends JFrame {
	private JPanel contentPane;
	public static JTextField txtValor;
	public static JDateChooser txtFechaE;
	public static JDateChooser txtFechaS;
	public static JComboBox<Format> txtFormaPago;
	int xMouse, yMouse;
	private JLabel labelExit;
	private JLabel lblValorSimbolo; 
	private JLabel labelAtras;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReservasView frame = new ReservasView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public ReservasView() {
		super("Reserva");
		setIconImage(Toolkit.getDefaultToolkit().getImage(ReservasView.class.getResource("/imagenes/aH-40px.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 560);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setUndecorated(true);
		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 910, 560);
		contentPane.add(panel);
		panel.setLayout(null);
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(144, 238, 144));
		separator_1_2.setBounds(68, 195, 289, 2);
		separator_1_2.setBackground(new Color(144, 238, 144));
		panel.add(separator_1_2);
		JSeparator separator_1_3 = new JSeparator();
		separator_1_3.setForeground(new Color(144, 238, 144));
		separator_1_3.setBackground(new Color(144, 238, 144));
		separator_1_3.setBounds(68, 453, 289, 2);
		panel.add(separator_1_3);
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setForeground(new Color(144, 238, 144));
		separator_1_1.setBounds(68, 281, 289, 11);
		separator_1_1.setBackground(new Color(144, 238, 144));
		panel.add(separator_1_1);
		txtFechaE = new JDateChooser();
		txtFechaE.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		txtFechaE.getCalendarButton().setBackground(new Color(144, 238, 144));
		txtFechaE.getCalendarButton().setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaE.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 12));
		txtFechaE.setBounds(68, 161, 289, 35);
		txtFechaE.getCalendarButton().setBounds(268, 0, 21, 33);
		txtFechaE.setBackground(Color.WHITE);
		txtFechaE.setBorder(new LineBorder(SystemColor.window));
		txtFechaE.setDateFormatString("yyyy-MM-dd");
		txtFechaE.setFont(new Font("Roboto", Font.PLAIN, 18));
		panel.add(txtFechaE);
		lblValorSimbolo = new JLabel("$");
		lblValorSimbolo.setBounds(82, 331, 17, 25);
		lblValorSimbolo.setForeground(new Color(144, 238, 144));
		lblValorSimbolo.setFont(new Font("Roboto", Font.BOLD, 17));
		panel.add(lblValorSimbolo);
		JLabel lblCheckIn = new JLabel("FECHA DE CHECK IN");
		lblCheckIn.setForeground(SystemColor.textInactiveText);
		lblCheckIn.setBounds(68, 136, 169, 14);
		lblCheckIn.setFont(new Font("Roboto Black", Font.PLAIN, 15));
		panel.add(lblCheckIn);
		JLabel lblCheckOut = new JLabel("FECHA DE CHECK OUT");
		lblCheckOut.setForeground(SystemColor.textInactiveText);
		lblCheckOut.setBounds(68, 221, 187, 14);
		lblCheckOut.setFont(new Font("Roboto Black", Font.PLAIN, 15));
		panel.add(lblCheckOut);
		txtFechaS = new JDateChooser();
		txtFechaS.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		txtFechaS.getCalendarButton().setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaS.getCalendarButton().setFont(new Font("Roboto", Font.PLAIN, 11));
		txtFechaS.setBounds(68, 246, 289, 35);
		txtFechaS.getCalendarButton().setBounds(267, 1, 21, 31);
		txtFechaS.setBackground(Color.WHITE);
		txtFechaS.setFont(new Font("Roboto", Font.PLAIN, 18));
		txtFechaS.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				try {
					calcularValor();
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		txtFechaS.setDateFormatString("yyyy-MM-dd");
		txtFechaS.getCalendarButton().setBackground(new Color(144, 238, 144));
		txtFechaS.setBorder(new LineBorder(new Color(255, 255, 255), 0));
		panel.add(txtFechaS);
		txtValor = new JTextField();
		txtValor.setBackground(SystemColor.text);
		txtValor.setHorizontalAlignment(SwingConstants.LEFT);
		txtValor.setForeground(Color.BLACK);
		txtValor.setBounds(100, 328, 110, 33);
		txtValor.setEditable(false);
		txtValor.setFont(new Font("Roboto Black", Font.PLAIN, 15));
		txtValor.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		panel.add(txtValor);
		txtValor.setColumns(10);
		JLabel lblValor = new JLabel("VALOR DE LA RESERVA");
		lblValor.setForeground(SystemColor.textInactiveText);
		lblValor.setBounds(72, 303, 196, 14);
		lblValor.setFont(new Font("Roboto Black", Font.PLAIN, 15));
		panel.add(lblValor);
		txtFormaPago = new JComboBox();
		txtFormaPago.setBounds(68, 417, 289, 38);
		txtFormaPago.setBackground(new Color(255, 255, 255));
		txtFormaPago.setBorder(new LineBorder(new Color(255, 255, 255), 1, true));
		txtFormaPago.setFont(new Font("Roboto", Font.PLAIN, 15));
		txtFormaPago.setModel(new DefaultComboBoxModel(new String[] {"Tarjeta de Crédito", "Tarjeta de Débito", "Dinero en efectivo", "Cheque"}));
		panel.add(txtFormaPago);
		JLabel lblFormaPago = new JLabel("FORMA DE PAGO");
		lblFormaPago.setForeground(SystemColor.textInactiveText);
		lblFormaPago.setBounds(68, 382, 187, 24);
		lblFormaPago.setFont(new Font("Roboto Black", Font.PLAIN, 15));
		panel.add(lblFormaPago);
		JLabel lblTitulo = new JLabel("SISTEMA DE RESERVAS");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(68, 60, 289, 42);
		lblTitulo.setForeground(new Color(144, 238, 144));
		lblTitulo.setFont(new Font("Dialog", Font.BOLD, 20));
		panel.add(lblTitulo);
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(428, 0, 482, 560);
		panel_1.setBackground(new Color(144, 238, 144));
		panel.add(panel_1);
		panel_1.setLayout(null);
		JLabel logo = new JLabel("");
		logo.setBounds(197, 68, 104, 107);
		panel_1.add(logo);
		logo.setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/Ha-100px.png")));
		JLabel imagenFondo = new JLabel("");
		imagenFondo.setBounds(0, 140, 500, 409);
		panel_1.add(imagenFondo);
		imagenFondo.setBackground(Color.WHITE);
		imagenFondo.setIcon(new ImageIcon(ReservasView.class.getResource("/imagenes/reservas-img-3.png")));
		JPanel btnexit = new JPanel();
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int salir = JOptionPane.showConfirmDialog(null, "¿Deseas salir del programa?","Salir", JOptionPane.YES_NO_OPTION);				
				if(salir == 0 ) {
					System.exit(0);
				}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnexit.setBackground(Color.red);
				labelExit.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				btnexit.setBackground(new Color(144, 238, 144));
				labelExit.setForeground(Color.white);
			}
		});
		btnexit.setLayout(null);//
		btnexit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)); //prueba
		btnexit.setBackground(new Color(144, 238, 144));//
		btnexit.setBounds(429, 0, 53, 36);
		panel_1.add(btnexit);
		labelExit = new JLabel("X");
		labelExit.setForeground(Color.WHITE);
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		JPanel header = new JPanel();
		header.setBounds(0, 0, 910, 36);
		header.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				headerMouseDragged(e);
			}
		});
		header.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				headerMousePressed(e);
			}
		});
		header.setLayout(null);
		header.setBackground(Color.WHITE);
		panel.add(header);

		JPanel btnAtras = new JPanel();
		btnAtras.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MenuUsuario usuario = new MenuUsuario();
				usuario.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnAtras.setBackground(new Color(230,230,230));
				labelAtras.setForeground(Color.black);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				btnAtras.setBackground(Color.white);
				labelAtras.setForeground(Color.black);
			}
		});
		btnAtras.setLayout(null);
		btnAtras.setBackground(Color.WHITE);
		btnAtras.setBounds(0, 0, 53, 36);
		btnAtras.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		header.add(btnAtras);
		labelAtras = new JLabel("<");
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(new Color(144, 238, 144));
		separator_1.setBounds(68, 362, 289, 2);
		separator_1.setBackground(new Color(144, 238, 144));
		panel.add(separator_1);
		JPanel btnsiguiente = new JPanel();
		btnsiguiente.setLayout(null);
		btnsiguiente.setBackground(new Color(144, 238, 144));
		btnsiguiente.setBounds(238, 493, 122, 35);
		panel.add(btnsiguiente);
		btnsiguiente.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		JLabel lblSiguiente = new JLabel("SIGUIENTE");
		lblSiguiente.setHorizontalAlignment(SwingConstants.CENTER);
		lblSiguiente.setForeground(Color.WHITE);
		lblSiguiente.setFont(new Font("Roboto", Font.PLAIN, 15));
		lblSiguiente.setBounds(0, 0, 122, 35);
		btnsiguiente.add(lblSiguiente);
		btnsiguiente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (ReservasView.txtFechaE.getDate() != null && 
						ReservasView.txtFechaS.getDate() != null && 
						ReservasView.txtFechaS.getDate().getTime() > ReservasView.txtFechaE.getDate().getTime()) {
					int valorR = calcularValor();
					DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
					String fechaentradaR = formatter.format(ReservasView.txtFechaE.getDate()); 
					String fechasalidaR = formatter.format(ReservasView.txtFechaS.getDate()); 
					String formadepagoR = ReservasView.txtFormaPago.getSelectedItem().toString(); 
					Map<String, String> nuevaReserva = new HashMap<String, String>();
					nuevaReserva.put("fechaentrada", fechaentradaR);
					nuevaReserva.put("fechasalida", fechasalidaR);
					nuevaReserva.put("valor", String.valueOf(valorR));
					nuevaReserva.put("formadepago", formadepagoR);
					try {
						reserva.guardar(nuevaReserva); 
						RegistroHuesped registro = new RegistroHuesped();
						registro.setVisible(true);
						dispose();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Datos incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}	
			@Override
			public void mouseEntered(MouseEvent e) {
				btnsiguiente.setBackground(new Color(102, 205, 170));
				lblSiguiente.setForeground(Color.white);
			}			
			@Override
			public void mouseExited(MouseEvent e) {
				btnsiguiente.setBackground(new Color(144, 238, 144));
				lblSiguiente.setForeground(Color.white);
			}
		});
	}
	private void headerMousePressed(java.awt.event.MouseEvent evt) {
		xMouse = evt.getX();
		yMouse = evt.getY();
	}
	private void headerMouseDragged(java.awt.event.MouseEvent evt) {
		int x = evt.getXOnScreen();
		int y = evt.getYOnScreen();
		this.setLocation(x - xMouse, y - yMouse);
	}
	private int calcularValor() {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");			
		Date entrada = ReservasView.txtFechaE.getDate();
		Date salida = ReservasView.txtFechaS.getDate();
		long timein = salida.getTime() - entrada.getTime();
		TimeUnit time = TimeUnit.DAYS;
		long days = time.convert(timein, TimeUnit.MILLISECONDS);
		int valorReserva = ((int)days) * 350;
		txtValor.setText(String.valueOf(valorReserva));
		return valorReserva;	
	}
}