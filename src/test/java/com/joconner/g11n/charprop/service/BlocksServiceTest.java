/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.joconner.g11n.charprop.service;

import com.joconner.g11n.charprop.CharpropApplication;
import com.joconner.g11n.charprop.model.Block;
import java.util.List;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
/**
 *
 * @author joconner
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(CharpropApplication.class)
public class BlocksServiceTest {
   
    @Autowired
    BlocksService service;
    
    
    @Test
    public void testBlocksFileIsLoaded() {
        List<Block> blocks = service.getBlocks();
        assertNotNull(blocks);
        assertTrue(blocks.size() > 0);
    }
    
    @Test
    public void testBlocksFoundForDefinedChars() {
        List<Block> blocks = service.getBlocks();
        for(Block b: blocks) {
            int x = b.getBegin(); // char at beginning of block
            int y = b.getEnd();   // char at end of block
            int z = (x + y)/2;    // char in middle of block

            Block actual = service.getBlockFor(x);
            assertEquals(b, actual);

            actual = service.getBlockFor(y);
            assertEquals(b, actual);
            
            actual = service.getBlockFor(z);
            assertEquals(b, actual);
        }
    }
    
    @Test
    public void testNoBlockForUndefinedChars() {
        Block noBlock = service.getNoBlock();
        
        int[] ch = { Integer.MIN_VALUE, -1, 0x10fff, 0x104B0, 0x2CEB0, 0x110000, Integer.MAX_VALUE };
        
        for (int c : ch) {
            Block b = service.getBlockFor(c);
            assertEquals(noBlock, b);            
        }
                
    }
    
}
