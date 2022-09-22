package views;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Color;
import com.toedter.calendar.JDateChooser;
import db.huesped;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
@SuppressWarnings("serial")
public class RegistroHuesped extends JFrame {
	private JPanel contentPane;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JTextField txtNreserva;
	private JDateChooser txtFechaN;
	private JComboBox<Format> txtNacionalidad;
	private JLabel labelExit;
	private JLabel labelAtras;
	int xMouse, yMouse;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistroHuesped frame = new RegistroHuesped();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public RegistroHuesped() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(RegistroHuesped.class.getResource("/imagenes/lOGO-50PX.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 634);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.text);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setUndecorated(true);
		contentPane.setLayout(null);
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
		header.setBackground(SystemColor.text);
		header.setOpaque(false);
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
		txtNombre = new JTextField();
		txtNombre.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtNombre.setBounds(560, 135, 285, 33);
		txtNombre.setBackground(Color.WHITE);
		txtNombre.setColumns(10);
		txtNombre.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtNombre);
		txtApellido = new JTextField();
		txtApellido.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtApellido.setBounds(560, 204, 285, 33);
		txtApellido.setColumns(10);
		txtApellido.setBackground(Color.WHITE);
		txtApellido.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtApellido);
		txtFechaN = new JDateChooser();
		txtFechaN.getCalendarButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		txtFechaN.setBounds(560, 285, 285, 36);
		txtFechaN.getCalendarButton().setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/icon-reservas.png")));
		txtFechaN.getCalendarButton().setBackground(new Color(144, 238, 144));
		txtFechaN.setDateFormatString("yyyy-MM-dd");
		contentPane.add(txtFechaN);
		txtNacionalidad = new JComboBox();
		txtNacionalidad.setBounds(560, 355, 289, 36);
		txtNacionalidad.setBackground(new Color(255, 255, 255));
		txtNacionalidad.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtNacionalidad.setModel(new DefaultComboBoxModel(new String[] {"afgano-afgana", "alemán-", "alemana", "árabe-árabe", "argentino-argentina", "australiano-australiana", "belga-belga", "boliviano-boliviana", "brasileño-brasileña", "camboyano-camboyana", "canadiense-canadiense", "chileno-chilena", "chino-china", "colombiano-colombiana", "coreano-coreana", "costarricense-costarricense", "cubano-cubana", "danés-danesa", "ecuatoriano-ecuatoriana", "egipcio-egipcia", "salvadoreño-salvadoreña", "escocés-escocesa", "español-española", "estadounidense-estadounidense", "estonio-estonia", "etiope-etiope", "filipino-filipina", "finlandés-finlandesa", "francés-francesa", "galés-galesa", "griego-griega", "guatemalteco-guatemalteca", "haitiano-haitiana", "holandés-holandesa", "hondureño-hondureña", "indonés-indonesa", "inglés-inglesa", "iraquí-iraquí", "iraní-iraní", "irlandés-irlandesa", "israelí-israelí", "italiano-italiana", "japonés-japonesa", "jordano-jordana", "laosiano-laosiana", "letón-letona", "letonés-letonesa", "malayo-malaya", "marroquí-marroquí", "mexicano-mexicana", "nicaragüense-nicaragüense", "noruego-noruega", "neozelandés-neozelandesa", "panameño-panameña", "paraguayo-paraguaya", "peruano-peruana", "polaco-polaca", "portugués-portuguesa", "puertorriqueño-puertorriqueño", "dominicano-dominicana", "rumano-rumana", "ruso-rusa", "sueco-sueca", "suizo-suiza", "tailandés-tailandesa", "taiwanes-taiwanesa", "turco-turca", "ucraniano-ucraniana", "uruguayo-uruguaya", "venezolano-venezolana", "vietnamita-vietnamita"}));
		contentPane.add(txtNacionalidad);
		JLabel lblNombre = new JLabel("NOMBRE");
		lblNombre.setBounds(560, 112, 253, 14);
		lblNombre.setForeground(SystemColor.textInactiveText);
		lblNombre.setFont(new Font("Roboto Black", Font.PLAIN, 16));
		contentPane.add(lblNombre);
		JLabel lblApellido = new JLabel("APELLIDO");
		lblApellido.setBounds(560, 189, 255, 14);
		lblApellido.setForeground(SystemColor.textInactiveText);
		lblApellido.setFont(new Font("Roboto Black", Font.PLAIN, 16));
		contentPane.add(lblApellido);
		JLabel lblFechaN = new JLabel("FECHA DE NACIMIENTO");
		lblFechaN.setBounds(560, 256, 255, 17);
		lblFechaN.setForeground(SystemColor.textInactiveText);
		lblFechaN.setFont(new Font("Roboto Black", Font.PLAIN, 16));
		contentPane.add(lblFechaN);
		JLabel lblNacionalidad = new JLabel("NACIONALIDAD");
		lblNacionalidad.setBounds(560, 331, 255, 14);
		lblNacionalidad.setForeground(SystemColor.textInactiveText);
		lblNacionalidad.setFont(new Font("Roboto Black", Font.PLAIN, 16));
		contentPane.add(lblNacionalidad);
		JLabel lblTelefono = new JLabel("TELÉFONO");
		lblTelefono.setBounds(562, 411, 253, 16);
		lblTelefono.setForeground(SystemColor.textInactiveText);
		lblTelefono.setFont(new Font("Roboto Black", Font.PLAIN, 16));
		contentPane.add(lblTelefono);
		txtTelefono = new JTextField();
		txtTelefono.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtTelefono.setBounds(560, 429, 285, 33);
		txtTelefono.setColumns(10);
		txtTelefono.setBackground(Color.WHITE);
		txtTelefono.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtTelefono);
		JLabel lblTitulo = new JLabel("REGISTRO HUÉSPED");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(560, 46, 285, 42);
		lblTitulo.setForeground(new Color(144, 238, 144));
		lblTitulo.setFont(new Font("Roboto Black", Font.PLAIN, 20));
		contentPane.add(lblTitulo);
		JLabel lblNumeroReserva = new JLabel("NÚMERO DE RESERVA");
		lblNumeroReserva.setBounds(560, 479, 253, 14);
		lblNumeroReserva.setForeground(SystemColor.textInactiveText);
		lblNumeroReserva.setFont(new Font("Roboto Black", Font.PLAIN, 16));
		contentPane.add(lblNumeroReserva);
		txtNreserva = new JTextField();
		txtNreserva.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtNreserva.setBounds(560, 499, 285, 33);
		txtNreserva.setColumns(10);
		txtNreserva.setBackground(Color.WHITE);
		txtNreserva.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtNreserva);
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setBounds(560, 170, 289, 2);
		separator_1_2.setForeground(new Color(144, 238, 144));
		separator_1_2.setBackground(new Color(144, 238, 144));
		contentPane.add(separator_1_2);
		JSeparator separator_1_2_1 = new JSeparator();
		separator_1_2_1.setBounds(560, 240, 289, 2);
		separator_1_2_1.setForeground(new Color(144, 238, 144));
		separator_1_2_1.setBackground(new Color(144, 238, 144));
		contentPane.add(separator_1_2_1);
		JSeparator separator_1_2_3 = new JSeparator();
		separator_1_2_3.setBounds(560, 386, 289, 2);
		separator_1_2_3.setForeground(new Color(144, 238, 144));
		separator_1_2_3.setBackground(new Color(144, 238, 144));
		contentPane.add(separator_1_2_3);
		JSeparator separator_1_2_4 = new JSeparator();
		separator_1_2_4.setBounds(560, 462, 289, 2);
		separator_1_2_4.setForeground(new Color(144, 238, 144));
		separator_1_2_4.setBackground(new Color(144, 238, 144));
		contentPane.add(separator_1_2_4);
		JSeparator separator_1_2_5 = new JSeparator();
		separator_1_2_5.setBounds(560, 534, 289, 2);
		separator_1_2_5.setForeground(new Color(144, 238, 144));
		separator_1_2_5.setBackground(new Color(144, 238, 144));
		contentPane.add(separator_1_2_5);
		JPanel btnguardar = new JPanel();
		btnguardar.setBounds(723, 565, 122, 35);
		btnguardar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtNombre.getText().length() != 0 &&
						txtApellido.getText().length() != 0 &&
						txtFechaN.getDate() != null &&
						txtTelefono.getText().length() != 0 &&
						txtNreserva.getText().length() != 0) {
					String nombreH = txtNombre.getText();
					String apellidoH = txtApellido.getText();
					DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
					String fechanacimientoH = formatter.format(txtFechaN.getDate()); 
					String nacionalidadH = txtNacionalidad.getSelectedItem().toString(); 
					String telefonoH = txtTelefono.getText();
					int numerodereservaH =  Integer.parseInt(txtNreserva.getText()); 
					Map<String, String> nuevoHuesped = new HashMap<String, String>();
					nuevoHuesped.put("nombre", nombreH);
					nuevoHuesped.put("apellido", apellidoH);
					nuevoHuesped.put("nacimiento", fechanacimientoH);
					nuevoHuesped.put("nacionalidad", nacionalidadH);
					nuevoHuesped.put("telefono", telefonoH);
					nuevoHuesped.put("idreserva", String.valueOf(numerodereservaH));
					try {
						huesped.guardar(nuevoHuesped);
						dispose(); 
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Verifica los datos", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnguardar.setLayout(null);
		btnguardar.setBackground(new Color(144, 238, 144));
		contentPane.add(btnguardar);
		btnguardar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		JLabel labelGuardar = new JLabel("GUARDAR");
		labelGuardar.setHorizontalAlignment(SwingConstants.CENTER);
		labelGuardar.setForeground(Color.WHITE);
		labelGuardar.setFont(new Font("Roboto", Font.PLAIN, 16));
		labelGuardar.setBounds(0, 0, 122, 35);
		btnguardar.add(labelGuardar);
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 489, 634);
		panel.setBackground(new Color(144, 238, 144));
		contentPane.add(panel);
		panel.setLayout(null);
		JLabel imagenFondo = new JLabel("");
		imagenFondo.setBounds(0, 121, 479, 502);
		panel.add(imagenFondo);
		imagenFondo.setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/registro.png")));
		JLabel logo = new JLabel("");
		logo.setBounds(194, 39, 104, 107);
		panel.add(logo);
		logo.setIcon(new ImageIcon(RegistroHuesped.class.getResource("/imagenes/Ha-100px.png")));
		JPanel btnexit = new JPanel();
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		btnexit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int salir = JOptionPane.showConfirmDialog(null, "Si sales ahora se creará una Reserva sin un Huésped, ¿Deseas salir?","ADVERTENCIA", JOptionPane.YES_NO_OPTION);
				if(salir == 0) {
					JOptionPane.showMessageDialog(null, "Eliminar reserva sin huésped en la base de datos (recomendación)", "ADVERTENCIA",JOptionPane.WARNING_MESSAGE);
					Busqueda busqueda = new Busqueda();
					busqueda.setVisible(true);
					dispose();
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
		btnexit.setLayout(null);
		btnexit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)); 
		btnexit.setBackground(new Color(144, 238, 144));
		labelExit = new JLabel("X");
		labelExit.setBackground(new Color(255, 255, 255));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.WHITE);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
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
}