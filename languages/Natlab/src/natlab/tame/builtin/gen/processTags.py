import sys
# in this file the code that gets generated by the tags gets printed
# the genBuiltin.py calls, for every encountered tag, a function with
# the same name in this module. The function will always be called
# with the same arguments, and should return string representing java
# code that will by inserted in the code for the Builtin where the tag
# occured. A tag has a name, and arguments. See the builtins.csv and
# genBuiltin.py
#
# functions are called with the arguments (builtin(Builtin obj), tagArgs(string), implements-set(string-set))
DEBUG = True;
def processTags(builtin, iset):
    code = "";
    if (DEBUG and len(builtin.tags) > 0): print "\n***** tag processing for:",builtin.name,"*******\ndefined tags: ",builtin.tags
    for tagName in builtin.tags.keys():
       tagArgs = builtin.tags[tagName];
       # the function that deals with the tag needs to have the same name as the tag 
       try:
           f = eval(tagName)
       except:
           raise Exception("tag '"+tagName+"' used for function "+builtin.name+" is not defined");
       code += f(builtin, tagArgs, iset)
    return code;

# *** Helper Functions **********************************************

# takes a string s which should be a comma separated list, sorrounded
# by parenthesis or not, such that eval(s) is always a tuple
# - it strips, removes surrounding parthesis, and adds ',', unless it's
#   empty, in which case it returns '()'
# '(a,b)' -> 'a,b,'         '(a)' -> 'a,'      'a,b' -> 'a,b,'         
# '()'    -> '()'           ''    -> '()'
def makeArgString(s):
  s = s.strip()
  if (s[0] == '(' and s[-1] == ')'): s = s[1:-1]
  if (len(s) == 0): return '()'
  return s+','


# *** processing tags *********************************************************
def s(builtin, tagArgs, iset):
  return ''


def args(builtin, tagArgs, iset):
  # add the interface
  iset.add("ArityDefined");
  # parse arg
  dict = {'inf': 'ArityDefined.INFINITE'};
  if (len(tagArgs) == 0):minmax = ()
  else: minmax = eval(makeArgString(tagArgs),dict);
  print minmax
  # if only one arg is defined, we set the second to the first
  if (len(minmax) == 1): minmax = minmax+minmax
  if (len(minmax) == 0): return ""
  return """
        public int getMaxNumOfArgs(){{ return {0}; }}
        public int getMinNumOfArgs(){{ return {1}; }}
        public boolean isVariadic(){{ return {0}=={1}; }}
""".format(*minmax)


# **** class propagation tags *******************************************
# abstract defines whether a builtin is abstract, processed by genbuiltins itself
def abstract(builtin, tagArgs, iset):
    return ""


# the Class tag
def Class(builtin, tagArgs, iset):
    iset.add("HasClassPropagationInfo"); # add the interface
    treeString = tagArgs.strip()[1:-1];  # building string to parse - remove parentheses
    
    # java expr for parent info - find if tag 'Class' is defined for a parent
    if (builtin.parent and builtin.parent.getAllTags().has_key('Class')):
      parentInfo = 'super.getClassPropagationInfo()'
    else:
      parentInfo = 'new CPNone()'
    
    # deal with the matlabClass info - check if there is a matlabClass tag defined - if not, emit the default
    if (not builtin.getAllTags().has_key('MatlabClass')):
        matlabClassMethod = """
        public CP getMatlabClassPropagationInfo(){{
            return getClassPropagationInfo();
        }}
"""; # there's no explicit tag for matlab - just return the class info
    else:
        matlabClassMethod = ''; # emit nothing - the matlabClass tag will deal with it

    # produce code
    return matlabClassMethod+"""
        private CP classPropInfo = null;
        public CP getClassPropagationInfo(){{
            //set classPropInfo if not defined
            if (classPropInfo == null){{
                classPropInfo = ClassPropTool.parse("{treeString}");
                classPropInfo.setVar("parent",{parentInfo});
                classPropInfo.setVar("matlab",getMatlabClassPropagationInfo());
            }}
            return classPropInfo;
        }}
""".format(treeString=treeString, javaName=builtin.javaName, parentInfo=parentInfo);


# Matlab class tag
def MatlabClass(builtin, tagArgs, iset):
    iset.add("HasClassPropagationInfo"); # add the interface
    treeString = tagArgs.strip()[1:-1];  # building string to parse - remove parentheses

    # check whether a 'class' tag is defined for this builtin
    if not builtin.getAllTags().has_key('MatlabClass'):
        raise Exception('MatlabClass tag defined for builtin '+builtin.name+', but there is no Class tag defined')
    
    # java expr for parent info - find if tag 'Class' is defined for a parent
    if (builtin.parent and builtin.parent.getAllTags().has_key('MatlabClass')):
      parentInfo = 'super.getMatlabClassPropagationInfo()'
    else:
      parentInfo = 'new CPNone()'

    # produce code
    return """
        private CP matlabClassPropInfo = null;
        public CP getMatlabClassPropagationInfo(){{
            //set classPropInfo if not defined
            if (matlabClassPropInfo == null){{
                matlabClassPropInfo = ClassPropTool.parse("{treeString}");
                matlabClassPropInfo.setVar("parent",{parentInfo});
                matlabClassPropInfo.setVar("natlab",getClassPropagationInfo());
            }}
            return matlabClassPropInfo;
        }}
""".format(treeString=treeString, javaName=builtin.javaName, parentInfo=parentInfo);



def Shape(builtin, tagArgs, iset):
    iset.add("HasShapePropagationInfo"); # add 'has shape' to the implemented interfaces of the builtin
    shape = tagArgs.strip()[1:-1]; # get the shape info -- strip parentheses
    return """
        public Object getShapePropagationInfo(){{
            return "{shape}"; //XU - FIX THIS -- CHECK CLASS PROP EXAMPLE!!
        }}

""".format(shape=shape); # return the java method













# Idea for Vineet:
#s = tagArgs.strip()[1:-1]
#cases = s.split('||')
#for case in cases:
#   clause = case.split('->')
#   if (len(clause) < 2):
#       # perform replacements
#       tags = clause[0].split(',');
#       for tag in tas.split(',');
#         tag = tag.strip();
#         
#   else:
#       # there's an actual arrow expression

       
