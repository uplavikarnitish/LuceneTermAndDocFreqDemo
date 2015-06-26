package demo;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.*;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.BytesRef;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Iterator;

/**
 * Created by nuplavikar on 6/25/15.
 */
class PrintAllFilesTermVectors {

    public static void main(String [] args) throws IOException {
        String indexDir = args[0];
        String filename = args[1];
        IndexReader indexReader = null;
        try {
            indexReader = DirectoryReader.open(new SimpleFSDirectory(Paths.get(indexDir)));
        } catch (IndexNotFoundException e)
        {
            System.out.println("No files found in the index specified in directory = "+indexDir);
            System.exit(1);
        }

        int n =  indexReader.numDocs();
        System.out.println("Total number of indexed documents found = "+n);

        for ( int i = 0; i < n; i++  )
        {
            Document doc = indexReader.document(i);
            Fields fields = indexReader.getTermVectors(i);
            //Iterator<String> docFieldNameIterator =  fields.iterator();
            System.out.println("Printing terms for the file: "+doc.get("filename"));
            Terms terms = fields.terms("contents");

            System.out.println("\t\tTotal number of unique terms = "+terms.size());
            TermsEnum termsEnum = terms.iterator(null);



            BytesRef term;
            int count = 1;
            while ((term = termsEnum.next()) != null)
            {
                System.out.println(count+"> "+term.utf8ToString());
                count=count+1;
            }
        }

    }
}
