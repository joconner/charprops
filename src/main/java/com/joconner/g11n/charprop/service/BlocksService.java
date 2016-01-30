/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joconner.g11n.charprop.service;

import com.joconner.g11n.charprop.model.Block;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_SINGLETON;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 *
 * @author joconner
 */
@Service
@Scope(SCOPE_SINGLETON)
public class BlocksService {
        
    private List<Block> blocks = null;
    private Block noBlock = null;

    
    public BlocksService() {
            noBlock = new Block("No_Block", -1, -1);
            blocks = new ArrayList<>();
            InputStream blockInputStream = ClassLoader.getSystemResourceAsStream("unicode/Blocks.txt");
            InputStreamReader blockReader = new InputStreamReader(blockInputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(blockReader);
            reader.lines()
                .filter(s -> s.matches("^\\p{XDigit}{4,6}\\.\\.\\p{XDigit}{4,6};\\s.+"))
                .forEach((String t) -> {
                    int rangeIdx = t.indexOf("..");
                    int rangeEndIdx = t.indexOf(";");
                    int nameIdx = t.indexOf(" ", rangeIdx) +1;
                    int blockStart = Integer.parseInt(t.substring(0, rangeIdx), 16);
                    int blockEnd = Integer.parseInt(t.substring(rangeIdx +2, rangeEndIdx), 16);
                    Block b = new Block(t.substring(nameIdx), blockStart, blockEnd);
                    blocks.add(b);
                });
            try {
                reader.close();
            } catch(IOException ex) {
                
            }
            
    }
    
    public List<Block> getBlocks() {
        return blocks;
    }
    
    public Block getBlockFor(int ch) {
        Block b = noBlock;
        Block chBlock = new Block(null, ch, ch);
        int index = Collections.binarySearch(blocks, chBlock, (Block o1, Block o2) -> {
            int o1b = o1.getBegin();
            int o1e = o1.getEnd();
            int o2b = o2.getBegin();
            int o2e = o2.getEnd();
            
            if(o1b < o2b && o1e < o2e) {
                return -1;
            } else if (o1b > o2b && o1e > o2e) {
                return 1;
            } else {
                return 0;
            }
        });
        
        if (index >= 0 && index < blocks.size()) {
            b = blocks.get(index);
        }
        return b;
    }
    
    public Block getNoBlock() {
        return noBlock;
    }
    
    
    
}
