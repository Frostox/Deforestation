/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package deforestation;

import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JToggleButton;

/**
 *
 * @author Roger
 */
public class Deforestation extends Thread{
    MainWindow m;
    int FORESTS = 1,DESERTS = 0;
    int land[][];
    int n,no_deserts,y=0,a=0,b=0;
    Integer pool[];
    /**
     * @param args the command line arguments
     */
    public Deforestation(MainWindow mw){
        m = mw;
    }
    public void run() {
        n = m.no;
        land = m.matrix;
        try {
            deforest();
        } catch (Exception ex) {
            Logger.getLogger(Deforestation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void deforest() throws Exception{
        
        m.years.setText("Calculating...");
        m.jButton1.setEnabled(false);
        m.n.setEnabled(false);
        m.jPanel1.setEnabled(false);
        for(int i=0;i<n*n;i++){
            //((JToggleButton)m.buttons.elementAt(i)).setEnabled(false);
        }
        //n = nx;
        //land = landx;
        m.jTextArea1.setText("");
        do{
            m.jTextArea1.setCaretPosition(m.jTextArea1.getDocument().getLength());
        if(number_of(DESERTS) == 0){
            if(y==0){ m.jTextArea1.append("> number of Deserts is zero\n");m.jTextArea1.setCaretPosition(m.jTextArea1.getDocument().getLength());}
            break;
        }
        if(number_of(DESERTS) == n*n){
            if(y==0){ m.jTextArea1.append("> number of Forests is zero\n");m.jTextArea1.setCaretPosition(m.jTextArea1.getDocument().getLength());}
            break;
        }
        
        no_deserts = number_of(DESERTS);
        m.jTextArea1.append("> no of deserts = "+no_deserts+"\n");m.jTextArea1.setCaretPosition(m.jTextArea1.getDocument().getLength());
        pool = getDeserts();
        int max = 0;
        int indexi=0,indexj=0;
        m.years.setText(y+" YEARS");
        m.jTextArea1.append("> number of years:"+y+"\n");m.jTextArea1.setCaretPosition(m.jTextArea1.getDocument().getLength());
        m.jTextArea1.append("> promising deserts:\n ");m.jTextArea1.setCaretPosition(m.jTextArea1.getDocument().getLength());
       
        for(int i = 0;i<pool.length;i++){
            if(pool[i] != null){
                //System.out.print(pool[i]+"  ");
                a = pool[i]/n;
                b = pool[i]%n;
                
                
                
                int temp = checkNeighbourCount(a,b);
                if(temp!=0){
                ((JToggleButton)m.buttons.elementAt(a*n+b)).setText("("+a+","+b+")");
                m.jTextArea1.append("> ("+a+","+b+")\n");m.jTextArea1.setCaretPosition(m.jTextArea1.getDocument().getLength());
                sleep(m.speeds[Integer.parseInt(m.sp.getValue().toString())]);
                //this.sleep();
                ((JToggleButton)m.buttons.elementAt(a*n+b)).setText("");
                }
                if(temp>max){indexi = a;indexj = b;max = temp;}
                
                
                
            
            }else{
            m.jTextArea1.append("Selected Desert :("+indexi+","+indexj+")\n");
            ((JToggleButton)m.buttons.elementAt((indexi*n)+indexj)).setText("S");    
            break;}
        }
        //System.out.println("("+indexi+","+indexj+") ");
        y++;
        if(in(indexi-1)){land[indexi-1][indexj] = 0;
        ((JToggleButton)(m.buttons.elementAt(((indexi-1)*n)+indexj))).setSelected(false);sleep(m.speeds[Integer.parseInt(m.sp.getValue().toString())]);
        }
        
        
        
        if(in(indexi+1)){land[indexi+1][indexj] = 0;
        ((JToggleButton)(m.buttons.elementAt(((indexi+1)*n)+indexj))).setSelected(false);sleep(m.speeds[Integer.parseInt(m.sp.getValue().toString())]);}
        
        
        
        if(in(indexj-1)){land[indexi][indexj-1] = 0;
        ((JToggleButton)(m.buttons.elementAt((indexi*n)+(indexj-1)))).setSelected(false);sleep(m.speeds[Integer.parseInt(m.sp.getValue().toString())]);}
        
        
        
        if(in(indexj+1)){land[indexi][indexj+1] = 0;
        ((JToggleButton)(m.buttons.elementAt((indexi*n)+(indexj+1)))).setSelected(false);sleep(m.speeds[Integer.parseInt(m.sp.getValue().toString())]);}
        
        
        
        if(in(indexi-1)&&in(indexj-1)){land[indexi-1][indexj-1] = 0;
        ((JToggleButton)(m.buttons.elementAt(((indexi-1)*n)+(indexj-1)))).setSelected(false);sleep(m.speeds[Integer.parseInt(m.sp.getValue().toString())]);}
        
        
        
        if(in(indexi+1)&&in(indexj+1)){land[indexi+1][indexj+1] = 0;
        ((JToggleButton)(m.buttons.elementAt(((indexi+1)*n)+(indexj+1)))).setSelected(false);sleep(m.speeds[Integer.parseInt(m.sp.getValue().toString())]);}
        
        
        
        if(in(indexi+1)&&in(indexj-1)){land[indexi+1][indexj-1] = 0;
        ((JToggleButton)(m.buttons.elementAt(((indexi+1)*n)+(indexj-1)))).setSelected(false);sleep(m.speeds[Integer.parseInt(m.sp.getValue().toString())]);}
        
        
        if(in(indexi-1)&&in(indexj+1)){land[indexi-1][indexj+1] = 0;
        ((JToggleButton)(m.buttons.elementAt(((indexi-1)*n)+(indexj+1)))).setSelected(false);sleep(m.speeds[Integer.parseInt(m.sp.getValue().toString())]);}
        
        ((JToggleButton)m.buttons.elementAt((indexi*n)+indexj)).setText("");
        
        
        }while(true);
        m.years.setText(y+" YEARS");
        sleep(1000);
        
        
        m.jButton1.setEnabled(true);
        m.n.setEnabled(true);
        m.jPanel1.setEnabled(true);
        
        for(int i=0;i<n*n;i++){
            ((JToggleButton)m.buttons.elementAt(i)).setSelected(true);
            m.jLabel6.setText("");
            m.jLabel7.setText("");
            int a = Integer.parseInt(m.n.getSelectedItem().toString());
        
            int temp = (int)Math.pow(a, 2);
            m.jLabel6.setText(""+temp);
            m.jLabel7.setText("0");
        }
        
    }
    public int checkNeighbourCount(int i, int j){
        int count = 0;
        if(in(i-1)){count = land[i-1][j] + count;}
        if(in(i+1)){count = land[i+1][j] + count;}
        if(in(j-1)){count = land[i][j-1] + count;}
        if(in(j+1)){count = land[i][j+1] + count;}
        if(in(i-1)&&in(j-1)){count = land[i-1][j-1] + count;}
        if(in(i+1)&&in(j+1)){count = land[i+1][j+1] + count;}
        if(in(i+1)&&in(j-1)){count = land[i+1][j-1] + count;}
        if(in(i-1)&&in(j+1)){count = land[i-1][j+1] + count;}
        //System.out.println("("+i+","+j+") "+count+" ");
        return count;
            
    }
    public boolean in(int x){
        if(x>=0 && x<n){
            return true;
        }
        return false;
    }
    public Integer[] getDeserts(){
        int x = 0;
        Integer deserts[] = new Integer[n*n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(land[i][j] == DESERTS){
                    deserts[x] = (i*n)+j;
                    x++;
                }
            }
        }
        return deserts;
        
    }
    public int number_of(int item){
        int count = 0;
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(item == land[i][j])
                    count++;
            }
        }
        return count;
    }
}
