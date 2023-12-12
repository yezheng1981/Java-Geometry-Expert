<h1><a class="pagetitle" title="refresh" accesskey="2" href="http://www.cs.wichita.edu/~ye/">What Is JGEX?</a></h1>

<div class="wikitopline"></div>

<div class="wikitext">
  <div width="30%" height="90" style="width: 45%; color: rgb(0, 0, 0);"><span style="width: 280px; text-align: center; white-space: nowrap; color: rgb(0, 0, 0);"><img src="src/docs/help/images/headline.jpg" alt="Java Geometry Expert" width="218" height="117" /></span></div>
  <p>JGEX  is a software which combines dynamic geometry software (DGS), automated  geometry theorem prover (GTP) and our approach for visually dynamic  presentation of proofs. As a dynamic geometry software, JGEX can be  used to build dynamic visual models to assist teaching and learning of  various mathematical concepts. As an automated reasoning software, we  can build dynamic logic models which can do reasoning themselves. As a  tool for dynamic presentation of proofs, JGEX is a valuable for  teachers and students to write and present proofs of geometry theorems  with various dynamic visual effects.</p>
  <ol>
    <li>JGEX is a powerful software for <em>geometric reasoning</em>.  Within its domain, it invites comparison with the best of human  geometry provers. It implements most of the effective methods for  geometric reasoning introduced in the past twenty years, including the  deductive base method, Wu's method, and the full-angle method, etc.  With these methods, users may automated prove geometry theorems, to  discover new properties of theorems, and to generate readable proofs  for many geometry theorems. </li>
    <li> By its dynamic  nature, the diagram built by this softwares can be changed dynamically.  With JGEX, we can drag part of the diagram with mouse and see  immediately how the diagram changes accordingly.</li>
    <li>JGEX  can be used to create proofs either manually and automatically. It  provides a series of visual effects for presenting of these proof.</li>
  </ol>
  <p><strong>JGEX</strong> consists of three parts: <strong>the drawing part</strong>, <strong>the reasoning and                proving part</strong>, and the <strong>part of the visual presentation of proofs</strong>. In                the drawing part, JGEX provides a graphical interface for the user                to draw the diagram step by step with predefined constructions. Wu's                method, the Full Angle Method and the Deductive Database Method                based on Full Angle are implemented in JGEX as  reasoning and                proving tool. </p>
  <p>The part of visual presentation of proofs makes JGEX most                distinctive from other geometry drawing systems on one side, and                from other geometry reasoning systems, including our previous                versions of <strong>GEX</strong>, on the other side. It is based on our work on                automated generation of readable proofs and on our approach to                geometric drawing.</p>
  <p>&nbsp;</p>
  <h3><strong>1. A Dynamic Geometry Software </strong> <a name="dgs" id="dgs"></a></h3>
  <p>There have been excellent commercial geometry theorem drawing systems such as the Geometer’s          Sketchpad in the US, Cabri in France, and Cinderella in Germany. All of them are          capable of doing dynamic geometry. Each of them has its own advantages and extends to other          areas such as drawing in 3D geometry, etc.          The name “dynamic geometry” was introduced as early as 1950 in the book:<br />
  </p>
  <blockquote>
    <p align="center" class="codelisting STYLE1">By a dynamic geometry we simply mean a study of the parts of space andtheir relations            to one another while they are in motion and changing.</p>
  </blockquote>
  <p> The drawing part of JGEX allows the user to construct the diagram interactively and manipulate          the diagram in a dynamic way, so JGEX is first a DGS. Starting from free points, the user can          create elements which is dependent on existed elements. With the mouse, the user can place points,          draw lines, introduce marks, etc. In this way, the diagram is constructed step by step. Much more          important is the fact that the user can explore the dynamic nature of the diagram. The user can drag           part of the diagram with mouse and see immediately how the diagram changes accordingly.          However, JGEX has its distinctive features comparing to the three commercial geometry drawing          systems.</p>
  <p><a href="src/docs/help/dynamic_geometry_software.html">See Detail &gt;&gt;&gt;</a> </p>
  <h3>2. An Automated Geometry Theorem Prover <a name="gtp" id="gtp"></a></h3>
  <p>Wu's          method, the Full Angle Method and the Deductive Database Method          based on Full Angle are implemented in JGEX as  reasoning and          proving tool.</p>
  <p><a href="src/docs/help/automated_theorem_prover.html">See Detail &gt;&gt;&gt; </a></p>
  <h3>3. A Tool for Visual Presentation of Proofs <a name="vpp" id="vpp"></a></h3>
  <p>The part of visual presentation of proofs makes JGEX most           distinctive from other geometry drawing systems on one side, and           from other geometry reasoning systems, including our previous          versions of GEX, on the other side. It is based on our work on          automated generation of readable proofs and on our approach to          geometric drawing.</p>
  <p>However,  as a first step, instead of automated generation of visual  presentations of proofs, we implement the manual input method for  creating visual presentations of proofs. This gives us first-hand  experience with the approach we propose. It is also an important  preparation for our future work on the proof checker. Especially, we  have a collection of over 100 examples created manually with JGEX. We  collect mainly those examples that do not mix algebraic expressions or  computations with the geometry diagrams.</p>
  <p><a href="src/docs/help/dynamic_presentation_of_proofs.html">See Detail &gt;&gt;&gt; </a></p>
  <p>&nbsp;</p>
  <p><strong>See Also: </strong></p>
  <ul>
    <li><a title="no description" href='src/docs/help/gex_jgex.html' class='wiki'>GEX and JGEX </a></li>
    <li> <a title="no description" href='src/docs/help/dynamic_geometry_software.html' class='wiki'>A Dynamic Geometry Software</a></li>
    <li> <a title="no description" href='src/docs/help/automated_theorem_prover.html' class='wiki'>An Automated
      Geometry Theorem Prover</a></li>
    <li> <a title="no description" href='src/docs/help/dynamic_presentation_of_proofs.html' class='wiki'>A  Tool for  Visually Dynamic
      Presentation of Proofs</a></li>
  </ul>
  <p>&nbsp;</p>
  
  <h3>4. How to use <a name="vpp" id="vpp"></a></h3>
  
  <p>
  <a href="https://github.com/kovzol/Java-Geometry-Expert/releases">Download JGEX</a> for your platform and just start it.
  In case you want to contribute to the development,
  you need to have some experience in the Java language.
  JGEX is written in Java, so you need a JDK in order to compile the code.
  The preferred IDE for development is
  <a href="https://www.jetbrains.com/idea">IntelliJ IDEA</a>. Alternatively, you can use <a href="https://gradle.org/">Gradle</a> on command line
  to compile and run the application: you need to type <b>gradlew run</b> in the root folder of JGEX.
  </p>
  
  <h3>5. The interface of JGEX <a name="vpp" id="vpp"></a></h3>
  <p><img src="src/docs/help/images3/jgex.gif" width="802" height="645" border="1" /><br />
  </p>
  
  <p>Some interesting animations</p>
  <img src="src/docs/help/effects/st.gif" border="1" height="180" width="400" />
  <img src="src/docs/help/effects/cg.gif" border="1" height="180" width="400" />
  <img src="src/docs/help/effects/p3.gif" border="1" height="180" width="400" />
  <img src="src/docs/help/effects/p4.gif" border="1" height="180" width="400" />
  <img src="src/docs/help/effects/p5.gif" border="1" height="180" width="400" />
  <img src="src/docs/help/effects/p6.gif" border="1" height="180" width="400" />

<table width="200" border="0" align="center">
  <tr>
    <td><p align="center"><img src="src/docs/help/images2/pyth1.gif" width="698" height="378" border="1" /><br />
            <br />
            <strong>A Manually Created Proof for Pythagoras Theorem. </strong> </p></td>
  </tr>
</table>

<p><strong>How to install and develop</strong></p>
<p>Since version 0.81, JGEX comes with a Gradle configuration.
It is suggested using IntelliJ IDEA to open Java Geometry Expert. The main class is in wprover.GExpert.</p>
<p>The newest versions of JGEX use the gettext internationalization system for translating the texts.
You need to install the gettext utility before starting a build. The msgfmt utility must be available in the system path.</p>
<p>JGEX uses a couple of additional third-party libraries, including graph-support (which requires slf4j and logback) and
batik (which uses xml-apis and xmlgraphics).
</p>
</div>

<p><strong>About this fork</strong></p>
This fork is maintained by Zoltán Kovács (PHDL Linz, Austria). The following contributors helped adding/fixing
some features in this version:
<ul>
 <li>Jorge Cassio (Portuguese translation)
 <li>Benedek Kovács (Localization support)
 <li>Predrag Janicic (Serbian translation)
 <li>Jelena Markovic (Serbian translation)
 <li>Alexander Thaller (German translation)
 <li>Alexander Vujic (Serbian translation)
 <li>Engelbert Zeintl (German translation)
</ul>
