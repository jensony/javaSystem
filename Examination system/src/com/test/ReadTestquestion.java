package com.test;


import java.io.*;
import java.util.*;
public class ReadTestquestion
{  String filename="",         
   correctAnswer="",           
   testContent="" ,            
   selection="" ;              
   int score=0;               
   long time=0;               
   boolean ��ɿ���=false;    
   File f=null;
   FileReader in=null;
   BufferedReader ��ȡ=null;
  public void setFilename(String name)
      {   filename=name; 
           
           
            score=0;
            selection="";
          try { 
                if(in!=null&&��ȡ!=null)
                  {
                     in.close();             
                     ��ȡ.close();
                  }
                f=new File(filename);
                in=new FileReader(f);
                ��ȡ=new BufferedReader(in);            
                correctAnswer=(��ȡ.readLine()).trim(); 
                String temp=(��ȡ.readLine()).trim()  ; 
                StringTokenizer token=new StringTokenizer(temp,":");
                int hour=Integer.parseInt(token.nextToken()) ;      
                int minute=Integer.parseInt(token.nextToken());    
                int second=Integer.parseInt(token.nextToken());    
                time=1000*(second+minute*60+hour*60*60);           
               
              }
           catch(Exception e)
              {
                testContent="û��ѡ������";
              }  
      }
  public String getFilename()
      { 
         return filename;
      }
  public long getTime()
      {
         return time;
      }
  public void set��ɿ���(boolean b)
      {
        ��ɿ���=b;
      }
  public boolean get��ɿ���()
      {
        return ��ɿ���;
      } 
  public String getTestContent()  
        { try {  
                 String s=null;
                 StringBuffer temp=new StringBuffer();
               if(��ȡ!=null)                       
                  {
                   while((s=��ȡ.readLine())!=null) 
                     { 
                       if(s.startsWith("**")) 
                           break;
                       temp.append("\n"+s);
                       if(s.startsWith("endend")) 
                        {
                          in.close();             
                          ��ȡ.close();  
                          ��ɿ���=true;         
                        }
                   }
                  testContent=new String(temp); 
                  }
               else
                  {
                     testContent=new String("û��ѡ������");
                  } 
              } 
          catch(Exception e)
              { 
                 testContent="��������Ϊ��,���Խ�������";
              }
          return testContent;
        }
  public void setSelection(String s)
        {  
           selection=selection+s; 
        }
  public int getScore()
        {  score=0;
           int length1=selection.length();   
           int length2=correctAnswer.length();
           int min=Math.min(length1,length2);
           for(int i=0;i<min;i++)
              { try{  
                    if(selection.charAt(i)==correctAnswer.charAt(i))
                          score++;
                   }
                catch(StringIndexOutOfBoundsException e) 
                   { 
                      i=0;
                   }
              }
           return score;
        }
   public String getMessages()
        {
          int length1=selection.length();  
          int length2=correctAnswer.length();
          int length=Math.min(length1,length2);
          String message="��ȷ��:"+correctAnswer.substring(0,length)+"\n"+
                         "��Ļش�:"+selection+"\n";
          return message;
        }
   
}

