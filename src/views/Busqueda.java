package views;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import db.reserva;
import db.huesped;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import java.awt.Toolkit;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.ListSelectionModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.sql.SQLException;
import java.awt.Cursor;
@SuppressWarnings("serial")
public class Busqueda extends JFrame {
	private JPanel contentPane;
	private JTextField txtBuscar;
	private JTable tbHuespedes;
	private JTable tbReservas;
	private DefaultTableModel modelo;
	private DefaultTableModel modeloH;
	private JLabel labelAtras;
	private JLabel labelExit;
	int xMouse, yMouse;
	private reserva reserva;
	private huesped huesped;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Busqueda frame = new Busqueda();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Busqueda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 910, 571);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setUndecorated(true);
		txtBuscar = new JTextField();
		txtBuscar.setBounds(536, 127, 193, 31);
		txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		txtBuscar.setFont(new Font("Roboto", Font.PLAIN, 16));
		txtBuscar.setText("Id de reserva");
		txtBuscar.setForeground(new Color(218,165,32,100));
		txtBuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (txtBuscar.getText().equals("Id de reserva")) {
					txtBuscar.setText("");
					txtBuscar.setForeground(Color.black);
				}
			}
		});
		JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setForeground(new Color(144, 238, 144));
		lblNewLabel_4.setFont(new Font("Dialog", Font.BOLD, 25));
		lblNewLabel_4.setBounds(285, 63, 337, 42);
		contentPane.add(lblNewLabel_4);
		JTabbedPane panel = new JTabbedPane(JTabbedPane.TOP);
		panel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.setBackground(new Color(255, 255, 255));
		panel.setFont(new Font("Dialog", Font.PLAIN, 18));
		panel.setBounds(20, 169, 865, 328);
		contentPane.add(panel);
		tbReservas = new JTable();
		tbReservas.setRowHeight(25);
		tbReservas.setSelectionBackground(new Color(173, 237, 205));
		tbReservas.setGridColor(new Color(102, 205, 170));
		tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
		JScrollPane paneR = new JScrollPane(tbReservas);
		panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), paneR, null);
		modelo = (DefaultTableModel) tbReservas.getModel();
		modelo.addColumn("Numero de Reserva");
		modelo.addColumn("Fecha Check In");
		modelo.addColumn("Fecha Check Out");
		modelo.addColumn("Valor");
		modelo.addColumn("Forma de Pago");
		cargarTablaReserva();
		tbHuespedes = new JTable();
		tbHuespedes.setRowHeight(25);
		tbHuespedes.setSelectionBackground(new Color(173, 237, 205));
		tbHuespedes.setGridColor(new Color(102, 205, 170));
		tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
		JScrollPane paneH = new JScrollPane(tbHuespedes);
		panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), paneH, null);
		modeloH = (DefaultTableModel) tbHuespedes.getModel();
		modeloH.addColumn("Numero de Huesped");
		modeloH.addColumn("Nombre");
		modeloH.addColumn("Apellido");
		modeloH.addColumn("Fecha de Nacimiento");
		modeloH.addColumn("Nacionalidad");
		modeloH.addColumn("Telefono");
		modeloH.addColumn("Numero de Reserva");
		cargarTablaHuesped();
		DefaultTableCellRenderer rendar1 = new DefaultTableCellRenderer();
		DefaultTableCellRenderer rendar2 = new DefaultTableCellRenderer();	
	    rendar1.setForeground(new Color(218,165,32));	  
	    tbReservas.getColumnModel().getColumn(0).setCellRenderer(rendar1);
	    tbHuespedes.getColumnModel().getColumn(6).setCellRenderer(rendar1);
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
		lblNewLabel_2.setBounds(56, 51, 104, 107);
		contentPane.add(lblNewLabel_2);
		JPanel header = new JPanel();
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
		header.setBounds(0, 0, 910, 36);
		contentPane.add(header);
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
		labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
		labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
		labelAtras.setBounds(0, 0, 53, 36);
		btnAtras.add(labelAtras);
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
		btnexit.setLayout(null);
		btnexit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		btnexit.setBackground(new Color(144, 238, 144));
		btnexit.setBounds(857, 0, 53, 36);
		header.add(btnexit);
		labelExit = new JLabel("X");
		labelExit.setHorizontalAlignment(SwingConstants.CENTER);
		labelExit.setForeground(Color.white);
		labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
		labelExit.setBounds(0, 0, 53, 36);
		btnexit.add(labelExit);
		JSeparator separator_1_2 = new JSeparator();
		separator_1_2.setForeground(new Color(144, 238, 144));
		separator_1_2.setBackground(new Color(144, 238, 144));
		separator_1_2.setBounds(539, 159, 193, 2);
		contentPane.add(separator_1_2);
		JPanel btnbuscar = new JPanel();
		btnbuscar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(txtBuscar.getText().length() == 0 || txtBuscar.getText().equals("Id de reserva")) {
					limpiarTablas();
					cargarTablaReserva();
					cargarTablaHuesped();
				}
				else {
					limpiarTablas();
					buscarID(txtBuscar.getText());	
				}
			}
		});
		btnbuscar.setLayout(null);
		btnbuscar.setBackground(new Color(144, 238, 144));
		btnbuscar.setBounds(748, 125, 122, 35);
		btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnbuscar);
		JLabel lblBuscar = new JLabel("Buscar");
		lblBuscar.setBounds(0, 0, 122, 35);
		btnbuscar.add(lblBuscar);
		lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
		lblBuscar.setForeground(Color.WHITE);
		lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18)); 
		JPanel btnEditarR = new JPanel();
		btnEditarR.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int seleccion = tbReservas.getSelectedRow();
				if(seleccion != -1) { 
					String idModificarR = (String) tbReservas.getModel().getValueAt(seleccion, 0);
					try {
						modificarTablaReserva(idModificarR);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Debes seleccionar la reserva modificada primero", "Advertencia", JOptionPane.WARNING_MESSAGE);
				}
			}
		}); 
		btnEditarR.setLayout(null);
		btnEditarR.setBackground(new Color(144, 238, 144));
		btnEditarR.setBounds(400, 508, 140, 35);
		btnEditarR.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditarR);
		JLabel lblEditar = new JLabel("Editar Reserva");
		lblEditar.setHorizontalTextPosition(SwingConstants.CENTER);
		lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditar.setForeground(Color.WHITE);
		lblEditar.setFont(new Font("Dialog", Font.BOLD, 16));
		lblEditar.setBounds(0, 0, 140, 35);
		btnEditarR.add(lblEditar);
		JPanel btnEditarH = new JPanel();
		btnEditarH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int seleccion = tbHuespedes.getSelectedRow();
				if(seleccion != -1) { 
					String idModificarH = (String) tbHuespedes.getModel().getValueAt(seleccion, 0);
					try {
						modificarTablaHuesped(idModificarH);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Debes seleccionar un huésped modificado primero", "Advertencia", JOptionPane.WARNING_MESSAGE);
				}
			}
		}); 
		btnEditarH.setLayout(null);
		btnEditarH.setBackground(new Color(144, 238, 144));
		btnEditarH.setBounds(585, 508, 140, 35);
		btnEditarH.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEditarH);
		JLabel lblEditarH = new JLabel("Editar Huésped");
		lblEditarH.setHorizontalTextPosition(SwingConstants.CENTER);
		lblEditarH.setHorizontalAlignment(SwingConstants.CENTER);
		lblEditarH.setForeground(Color.WHITE);
		lblEditarH.setFont(new Font("Dialog", Font.BOLD, 16));
		lblEditarH.setBounds(0, 0, 140, 35);
		btnEditarH.add(lblEditarH);
		JPanel btnEliminar = new JPanel();
		btnEliminar.setLayout(null);
		btnEliminar.setBackground(new Color(240, 128, 128));
		btnEliminar.setBounds(767, 508, 122, 35);
		btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		contentPane.add(btnEliminar);
		btnEliminar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int seleccion = tbReservas.getSelectedRow();
				if(seleccion != -1) {
					String idEliminarR = (String) tbReservas.getModel().getValueAt(seleccion, 0); 
					try {
						reserva.eliminar(idEliminarR);
						limpiarTablas();
						cargarTablaReserva();
						cargarTablaHuesped();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Selecciona una Reserva a eliminar", "Advertencia", JOptionPane.WARNING_MESSAGE);
				}
			}
		}); 
		JLabel lblEliminar = new JLabel("Eliminar");
		lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
		lblEliminar.setForeground(Color.WHITE);
		lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
		lblEliminar.setBounds(0, 0, 122, 35);
		btnEliminar.add(lblEliminar);
		setResizable(false);
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
	private void buscarID(String idBuscar){
		List<Map<String, String>> busquedaR = new ArrayList<Map<String, String>>();
		try {
			busquedaR = this.reserva.buscar(idBuscar);
			busquedaR.forEach(busqueda -> modelo.addRow(
					new Object[] {
							busqueda.get("id"),
							busqueda.get("fechaentrada"),
							busqueda.get("fechasalida"),
							busqueda.get("valor"),
							busqueda.get("formadepago")}));

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		List<Map<String, String>> busquedaH = new ArrayList<Map<String, String>>();
		try {
			busquedaH = this.huesped.buscar(idBuscar);
			busquedaH.forEach(busqueda -> modeloH.addRow(
					new Object[] {
							busqueda.get("id"),
							busqueda.get("nombre"),
							busqueda.get("apellido"),
							busqueda.get("nacimiento"),
							busqueda.get("nacionalidad"),
							busqueda.get("telefono"),
							busqueda.get("idreserva"),
					}));
		}catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}	
	}
	private void cargarTablaReserva() {
		List<Map<String, String>> reservas = new ArrayList<Map<String, String>>();
		try {
			reservas = this.reserva.listar();
			reservas.forEach(reserva -> modelo.addRow(
					new Object[] {
							reserva.get("id"),
							reserva.get("fechaentrada"),
							reserva.get("fechasalida"),
							reserva.get("valor"),
							reserva.get("formadepago")}));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	private void cargarTablaHuesped() {
		List<Map<String, String>> Huespedes = new ArrayList<Map<String, String>>();
		try {
			Huespedes = this.huesped.listar(); 
			Huespedes.forEach(huesped -> modeloH.addRow(
					new Object[] {
							huesped.get("id"),
							huesped.get("nombre"),
							huesped.get("apellido"),
							huesped.get("nacimiento"),
							huesped.get("nacionalidad"),
							huesped.get("telefono"),
							huesped.get("idreserva")}));
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	private void  limpiarTablas() {
		modelo.setRowCount(0);
		modeloH.setRowCount(0);
	}
	private void modificarTablaReserva(String idModificarR) throws SQLException {
		int seleccion = tbReservas.getSelectedRow();
		Map<String, String> modificarR = new HashMap<String, String>();
		modificarR.put("id", idModificarR);
		modificarR.put("fechaentrada", (String) tbReservas.getModel().getValueAt(seleccion, 1));
		modificarR.put("fechasalida", (String) tbReservas.getModel().getValueAt(seleccion, 2));
		modificarR.put("valor", (String) tbReservas.getModel().getValueAt(seleccion, 3));
		modificarR.put("formadepago", (String) tbReservas.getModel().getValueAt(seleccion, 4));
		reserva.modificar(modificarR);
		limpiarTablas();
		cargarTablaReserva();
		cargarTablaHuesped();
	}
	private void modificarTablaHuesped(String idModificarH) throws SQLException {
		int seleccion = tbHuespedes.getSelectedRow();
		Map<String, String> modificarH = new HashMap<String, String>();
		modificarH.put("id", idModificarH);
		modificarH.put("nombre", (String) tbHuespedes.getModel().getValueAt(seleccion, 1));
		modificarH.put("apellido", (String) tbHuespedes.getModel().getValueAt(seleccion, 2));
		modificarH.put("nacimiento", (String) tbHuespedes.getModel().getValueAt(seleccion, 3));
		modificarH.put("nacionalidad", (String) tbHuespedes.getModel().getValueAt(seleccion, 4));
		modificarH.put("telefono", (String) tbHuespedes.getModel().getValueAt(seleccion, 5));
		huesped.modificar(modificarH);
		limpiarTablas();
		cargarTablaReserva();
		cargarTablaHuesped();
	}
}
