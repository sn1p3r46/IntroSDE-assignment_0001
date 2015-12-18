
# Introsde 2015 Assignment 1

**Student:** Andrea Galloni ([Twitter](https://twitter.com/andreagalloni92))

**Organization:** [UniTN](http://www.unitn.it/en)

**Course** [Introduction to Service Design and Engineering](https://sites.google.com/site/introsdeunitn/)

<p align="center">
  <img src="https://lh5.googleusercontent.com/zNOrV6pkBTazzwZzSOLd4CX0QbpeLwOjyRBdiyMMq52j8OhoMx2atiwkVA3U2yPkwx_VLkKm4RwG4t0_fS7tBNec2lc04w9fliFyrKplBpowjThtu-IZtvqX" width="400">
</p>


<h2>Assignment Structure</h2>

The project is divided in different packages:

<ul>
<li><code>dao</code>: this package contains <code>PeopleStore.java</code> which stores and returns People object used for creating the JSON output trough the jackson library.</li>
<li><code>models</code>: this package contains <code>People.java</code> and <code>HealthProfile.java</code>, which are the representation of Person and HealthProfile objects and a <code>RandomHelper.java</code> class that provides some usefull methods for contructors (some of them are not used in this assignment)</li>
<li><code>assignment</code>: this package contains <code>Marshaller.java</code>, which provides the marshalling service, and <code>UnMarshaller.java</code> which provides the un-marshalling "service" </li>
<li><code>main</code>: this package contains <code>HealthProfileReader</code>, which provides methods for the lab3 based exercises and <code>PeopleStore.java</code> used to create the json output file</li>
</ul>


<h2>General information</h2>

<h4>Based on Lab 3</h4>
This part is handled by <code>HealthProfileReader.java</code>

<ol>
<li>In <code>HealthProfileReader.java</code> there are some methods such that <b>getXWeight()</b> and <b>getXHeight()</b>, which print respectively the weight and the height of a given ID containde in  <code>people.xml</code></li>
<li>Method <b>getAllPeople()</b> prints all people details contained in <code>people.xml</code></li>
<li>Method <b>displayHealthProfileX</b> that prints a person's health profile given an ID</li>
<li>Method <b>getWeightOp</b> that prints people's weight which satisfied a certain condition;</li>
<li>Method <b>getHeightOp</b> that prints people's Height which satisfied a certain condition;</li>
</ol>

<h4>Based on Lab 4</h4>
This part is handled three classes: <code>Marshaller.java</code>, <code>UnMarshaller.java</code>, <code>PeopleToJava.java</code>. <code>JAXB</code> is used to perform marshalling and un-marshalling of java objects.

<ol>
<li>The XML schema is contained in <code>people.xsd</code></li>
<li><code>Marshaller.java</code> does the marshalling from Java Objects to an XML file (people.xml)</li>
<li><code>UnMarshaller.java</code> does the un-marshalling from an XML file creates Java Objects and prints them</li>
<li><code>PeopleJson.java</code> does the marshalling from Java Objects to an Json file (people.json) and prints it</li>
</ol>

<h2>How to run the project</h2>

<b>ANT</b> is used to compile the project. <code>build.xml</code> is a file that <b>ANT</b> uses to prepare the environment for the execution. To manage dependences the <b>IVY</b> tool is used. It downloads libraries that are contained into the <code>ivy.xml</code> file.


<code>ant execute.evaluation</code>: download dependencies, creates java classes from <code>people.xsd</code> and run targets specified below:

<ul>
<li><code>ant execute.getAllPeople</code>: prints all people contained in <code>people.xml</code></li>
<li><code>ant execute.getHeightByCondition</code>: prints all people's heights that satisfy a condition <code>people.xml</code></li>
<li><code>ant execute.getHeightByCondition</code>: rints all people's weights that satisfy a condition <code>people.xml</code></li>
<li><code>ant execute.getHealthFromPid_5</code>: prints a person's health profile given an ID by running </li>
<li><code>ant execute.UnMarshaller</code>: executes the Un-marshalling to a XML file <code>people1.xml</code></li>
<li><code>ant execute.Marshaller</code>: executes the marshalling and  prints the result on screen</li>
<li><code>ant execute.PeopleToJson</code>: executes the Un-marshalling to the people.json file and  prints the result on screen</li>
</ul>

<p align="center">
  <img src="https://raw.githubusercontent.com/sn1p3r46/introsde-2015-assignment-3-client/master/images/LogoUniTn.png" width="300">
</p>

