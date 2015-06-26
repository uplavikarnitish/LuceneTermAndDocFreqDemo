package demo;

import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexOptions;

/**
 * Created by nuplavikar on 6/25/15.
 */
public class TextFieldWithTermVectors extends FieldType{


    public TextFieldWithTermVectors()
    {
        this.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
        this.setTokenized(true);
        this.setStoreTermVectors(true);
        this.setStoreTermVectorPositions(true);
        this.setStoreTermVectorOffsets(true);
    }
}
