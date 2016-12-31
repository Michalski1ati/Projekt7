package baza;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AdressBookPersonAddDB extends JFrame implements ActionListener {
	
	private JTextField field[];
	private JLabel label;
	
	public AdressBookPersonAddDB() {
		
		
		 setBounds(100, 100, 690, 240);
        setLayout(null);

        field = new JTextField[7];

        field[0] = new JTextField();
        field[0].setBorder(BorderFactory.createTitledBorder("Imię"));
        field[0].setBounds(10, 10, 200, 40);

        field[1] = new JTextField();
        field[1].setBorder(BorderFactory.createTitledBorder("Nazwisko"));
        field[1].setBounds(220, 10, 200, 40);

        field[2] = new JTextField();
        field[2].setBorder(BorderFactory.createTitledBorder("Ulica"));
        field[2].setBounds(10, 60, 300, 40);

        field[3] = new JTextField();
        field[3].setBorder(BorderFactory.createTitledBorder("Nr Domu"));
        field[3].setBounds(320, 60, 100, 40);

        field[4] = new JTextField();
        field[4].setBorder(BorderFactory.createTitledBorder("Nr Mieszkania"));
        field[4].setBounds(440, 60, 100, 40);

        field[5] = new JTextField();
        field[5].setBorder(BorderFactory.createTitledBorder("Miasto"));
        field[5].setBounds(330, 110, 200, 40);

        field[6] = new JTextField();
        field[6].setBorder(BorderFactory.createTitledBorder("Nr Telefonu"));
        field[6].setBounds(10, 110, 300, 40);
		
		
		for(JTextField f:field){
			f.setBackground(getBackground());
			add(f);
		}
		
		
		JButton button = new JButton("Dodaj");
		button.setBounds(10, 160, 100, 30);
		button.addActionListener(this);
		button.setActionCommand("dodaj");
		add(button);
		
		
		button = new JButton("Anuluj");
		button.setBounds(120, 160, 100, 30);
		button.addActionListener(this);
		button.setActionCommand("anuluj");
		add(button);
		
		
		label = new JLabel();
		label.setBounds(230, 160, 200, 30);
		label.setForeground( Color.red );
		add(label);
		
		setVisible(true);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		for(JTextField f:field)
			System.out.println(f.getText());
		
		if(e.getActionCommand().equals("dodaj")){
			
			int housenumber = -1;
			int flatnumber = -1;
			
			
			boolean error = false;
			try{
				housenumber = Integer.parseInt(field[3].getText().trim());
				
				Integer.parseInt(field[6].getText().trim());
				
				if(housenumber <= 0) error = true;
			}
			catch(NumberFormatException e1){
				error = true;
			}
			
			if(error){
				label.setText("Invalid data");
				return;
			}
			
			
			if(!field[4].getText().trim().equals("")){
				try{
					flatnumber = Integer.parseInt(field[4].getText().trim());
					
					if(flatnumber <= 0) error = true;
				}
				catch(NumberFormatException e1){
					error = true;
				}
				
				if(error){
					label.setText("Niepoprawne dane");
					return;
				}
				
			}
			else flatnumber = -1;
			
		
			if(flatnumber != -1){
				new PersonDB(field[0].getText(), field[1].getText(), field[2].getText(), housenumber, flatnumber, field[5].getText(), field[6].getText());
			}
			else
				new PersonDB(field[0].getText(), field[1].getText(), field[2].getText(), housenumber, field[5].getText(), field[6].getText());
			
			dispose();
			
		}
		else if(e.getActionCommand().equals("anuluj"))
			dispose();
		
	}

}
