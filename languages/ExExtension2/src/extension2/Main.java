// =========================================================================== //
//                                                                             //
// Copyright 2011 Jesse Doherty and McGill University.                         //
//                                                                             //
//   Licensed under the Apache License, Version 2.0 (the "License");           //
//   you may not use this file except in compliance with the License.          //
//   You may obtain a copy of the License at                                   //
//                                                                             //
//       http://www.apache.org/licenses/LICENSE-2.0                            //
//                                                                             //
//   Unless required by applicable law or agreed to in writing, software       //
//   distributed under the License is distributed on an "AS IS" BASIS,         //
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  //
//   See the License for the specific language governing permissions and       //
//  limitations under the License.                                             //
//                                                                             //
// =========================================================================== //

package extension2;

import ast.*;
import nodecases.*;

import natlab.toolkits.analysis.test.NameCollector;

/**
 * @author Jesse Doherty
 */
public class Main
{
    
    public static void main( String args[] )
    {
        System.out.println("in main!");

        Script s = new Script( new List(), 
                               new List().add( new IncStmt( new NameExpr( new Name("i") ) ) )
                               );

        System.out.println(s.getPrettyPrinted() );

        
        NameCollector nc = new NameCollector( s );
        nc.analyze();

        System.out.println( "nc: " + nc.getAllNames() );

        ExtendedNameCollector enc = new ExtendedNameCollector(s);
        enc.analyze();
        System.out.println( "enc: " + enc.getAllNames() );

    }

}
