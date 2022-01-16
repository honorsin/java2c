# java2c

网易云课堂 手写一个编译器的课

如何运行 

run thompsonConstructor

按照要求分别在控制台输入
1.正则表示式对照表
例 
D {0-9}
end
2.要解析的正则表达式
例
{D}*\.{D}|{d}\.{D}*
end

3.要解析的字符串
例
1.2


仓库建立较晚，初始commit包括的内容
词法分析
nfa的构造
nfa到dfa的转换
dfa压缩算法
