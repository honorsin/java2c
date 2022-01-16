# java2c

网易云课堂 手写一个编译器的课
\
1.如何运行  
  &ensp; run thompsonConstructor  
  &ensp; 按照要求分别在控制台输入  
  &ensp; 1.正则表示式对照表  
  &ensp; D    [0-9]  
  &ensp; end  
  &ensp; 2.要解析的正则表达式  
  &ensp; 例  
  &ensp; {D}*\.{D}|{d}\.{D}*  
  &ensp; end  

  &ensp; 3.要解析的字符串  
  &ensp; 例  
  &ensp; 1.2  

\
2.仓库建立较晚，初始commit包括的内容  
词法分析  
nfa的构造  
nfa到dfa的转换  
dfa压缩算法  
