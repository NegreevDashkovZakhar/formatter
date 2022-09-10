<h1>Homework</h1>
<h2>Formatter project</h2>
<p style="font-size: 22px">Java application for formatting java code</p>
<h3>How to run?</h3>
<ol>
<li>build source code with maven (junit, mockito and slf4j dependencies needed)</li>
<li>run jar-with-dependencies file with java</li>
<li>provide to console arguments for application
<ol>
<li>path and file name for to source (Example: folder/file.txt)</li>
<li>file name for destination file (Example: ../dest.txt)</li>
</ol>
WATCH OUT! error will occur if specified folder for destination file does not exist</li>
</ol>
<h3>Significant realisation details</h3>
<hr>
<p style="width: 500px">
Lexer and Formatter use state pattern for specifying logic for each
state. To decrease conditions usage maps for commands and states getting made. For
each state there is a pre-written instructions for each type of signal received</p>
<hr>
<p style="width: 500px">
Lexer first adds read tokens to queue and afterwards gets last 
from there. It is a need because if we have two tokens coming 
together with no separation we will be in a position where we 
have to add first token but we already started to read next token,
 so this character will be lost.
</p>
<p>Example : 
<span style="color: #ff0000">text</span><span style="color: #0000ff">;</span></p>
<hr>
