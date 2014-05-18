package org.oursight.study.javase.interfaceLearning;

import org.apache.commons.logging.Log;
import org.oursight.framework.yao.base.data.dao.ICommonDao;

import java.io.File;
import java.net.URL;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


/**
 * Powered by zms
 * 可以任意转载修改，也可以作为商业用途，但请在代码里保留上述标志
 */
public class ListAllImplementClass {
        
    @SuppressWarnings("unchecked")
        private static void testClass(String name,List<Class> list,ClassLoader loader,Class c) {
                try {
                        Class one=Class.forName(name,false,loader);
                        if(c.isAssignableFrom(one)) {
                                list.add(one);
                        }
                } catch (Throwable t) {                
                }
        }
        private static void loopPath(File parent,String name,List<Class> list,ClassLoader loader,Class c) {
                File[] files=parent.listFiles();
                for(File one:files) {
                        if(one.isFile()) {
                                String s=one.getName();
                                if(s.endsWith(".class")) {
                                        s=s.substring(0,s.indexOf('.'));
                                        if(name!=null) {
                                                s=name+"."+s;
                                        }
                                        testClass(s,list,loader,c);
                                }
                        }
                }
                for(File one:files) {
                        if(one.isDirectory()) {
                                String s=one.getName();
                                if(name!=null) {
                                        s=name+"."+s;
                                }
                                loopPath(one,s,list,loader,c);
                        }
                }
        }
        private static void loopJar(File file,List<Class> list,ClassLoader loader,Class c) {
                try {
                        ZipFile zip=new ZipFile(file);
                        for(Enumeration en=zip.entries();en.hasMoreElements();) {
                                String name=((ZipEntry)en.nextElement()).getName();
                                if(name.endsWith(".class")) {
                                        name=name.replace('/','.');
                                        name=name.substring(0,name.lastIndexOf('.'));                                  
                                        testClass(name,list,loader,c);
                                }
                        }
                        zip.close();
                } catch (Throwable t) {                
                }
        }      
        
        public static List<Class> getCompatibleClass(Class c) {
                if(c==null) {
                        return null;
                }
                List<Class> list=new ArrayList<Class>();
                ClassLoader loader=c.getClassLoader();
                if(loader!=null) {
                        String name=c.getName().replace('.','/')+".class";
                        URL url=loader.getResource(name);
                        if(url!=null) {
                                String path=url.getFile();
                                path=path.substring(0,path.length()-name.length()-1);
                                if(path.charAt(path.length()-1)=='!') {
                                        path=path.substring(0,path.length()-1);
                                }
                                path=path.substring(path.indexOf('/')+1);                              
                                File file=new File(path);
                                if(file.exists()) {
                                        if(file.isDirectory()) {
                                                loopPath(file,null,list,loader,c);
                                        } else if(file.isFile()) {
                                                loopJar(file,list,loader,c);                                            
                                        }
                                }
                        }
                }
                return list;
        }
        
        public static List<String> Paths=new ArrayList<String>();
        private synchronized static void initPaths() {
                if(Paths.size()<1) {
                        Properties prop=System.getProperties();
                        if(prop!=null) {
                                String classpath=(String)prop.getProperty("java.class.path") ;
                                String sep=(String)prop.getProperty("path.separator");
                                if(classpath!=null && sep!=null) {
                                        StringTokenizer token=new StringTokenizer(classpath,sep,false);
                                        while(token.hasMoreElements()) {
                                                Paths.add(token.nextToken());
                                        }
                                }
                        }
                }
        }
    public static List<Class> getAllCompatibleClass(Class c) {
        return getAllCompatibleClass(c,null,(String[])null);
    }
    public static List<Class> getAllCompatibleClass(Class c, Log log,String... excepts) {
                if(c==null) {
                        return null;
                }
                List<Class> list=new ArrayList<Class>();
                initPaths();
                ClassLoader loader=c.getClassLoader();
                if(loader==null) {
                    loader=ClassLoader.getSystemClassLoader();
                }
                if(loader!=null) {
                        out:for(String one:Paths) {
                                File file=new File(one);
                                if(file.exists()) {
                    if(log!=null) {
                        log.info(file);
                    }
                    if(file.isDirectory()) {
                                                loopPath(file,null,list,loader,c);
                                        } else if(file.isFile()) {
                        if(excepts!=null && excepts.length>0) {
                            String name=file.getName();
                            for(String ones:excepts) {
                                if(name.equals(ones) ) {
                                    continue out;
                                }
                            }
                        }
                        loopJar(file,list,loader,c);
                                        }
                                }
                        }
                }
                return list;
        }
    
    public static void main(String[] args) {
    	List<Class> all = ListAllImplementClass.getAllCompatibleClass(ICommonDao.class);
        for(Class one:all) {
            System.out.println(one);
        }
	}
}
