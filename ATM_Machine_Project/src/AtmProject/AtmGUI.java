package AtmProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AtmGUI extends JFrame
{
	    private Account account;
	    private JTextField accNoField;
	    private JPasswordField pinField;
	    private JLabel balanceLabel;

	    public AtmGUI(Account account) {
	        this.account = account;
	        initUI();
	    }

	    private void initUI() {
	        setTitle("ATM Machine");
	        setSize(400, 300);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);

	        JPanel panel = new JPanel();
	        panel.setLayout(new GridLayout(5, 2));

	        JLabel accNoLabel = new JLabel("Account Number:");
	        JLabel pinLabel = new JLabel("PIN:");
	        JLabel amountLabel = new JLabel("Amount:");

	        accNoField = new JTextField();
	        pinField = new JPasswordField();
	        JTextField amountField = new JTextField();

	        JButton withdrawButton = new JButton("Withdraw");
	        JButton depositButton = new JButton("Deposit");
	        JButton checkBalanceButton = new JButton("Check Balance");
	        JButton exitButton = new JButton("Exit");

	        balanceLabel = new JLabel("Balance: ");

	        panel.add(accNoLabel);
	        panel.add(accNoField);
	        panel.add(pinLabel);
	        panel.add(pinField);
	        panel.add(amountLabel);
	        panel.add(amountField);
	        panel.add(withdrawButton);
	        panel.add(depositButton);
	        panel.add(checkBalanceButton);
	        panel.add(exitButton);

	        add(panel, BorderLayout.CENTER);
	        add(balanceLabel, BorderLayout.SOUTH);

	        withdrawButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                performAction("withdraw", amountField.getText());
	            }
	        });

	        depositButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                performAction("deposit", amountField.getText());
	            }
	        });

	        checkBalanceButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                if (validateCredentials()) {
	                    balanceLabel.setText("Balance: " + account.getAccBalance());
	                }
	            }
	        });

	        exitButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                JOptionPane.showMessageDialog(null, "Thank you for using the ATM. Goodbye!");
	                System.exit(0);
	            }
	        });
	    }

	    private void performAction(String action, String amountText) {
	        if (validateCredentials()) {
	            try {
	                long amount = Long.parseLong(amountText);
	                if (action.equals("withdraw")) {
	                    account.withdraw(amount);
	                } else if (action.equals("deposit")) {
	                    account.deposit(amount);
	                }
	                balanceLabel.setText("Balance: " + account.getAccBalance());
	            } catch (NumberFormatException ex) {
	                JOptionPane.showMessageDialog(null, "Invalid amount.");
	            }
	        }
	    }

	    private boolean validateCredentials() {
	        long accNo = Long.parseLong(accNoField.getText());
	        int pin = Integer.parseInt(new String(pinField.getPassword()));
	        if (account.validateCredentials(accNo, pin)) {
	            return true;
	        } else {
	            JOptionPane.showMessageDialog(null, "Incorrect credentials.");
	            return false;
	        }
	    }

	    public static void main(String[] args) {
	        Account account = Account.createAccount();
	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                AtmGUI atmGui = new AtmGUI(account);
	                atmGui.setVisible(true);
	            }
	        });
	    }
	}


