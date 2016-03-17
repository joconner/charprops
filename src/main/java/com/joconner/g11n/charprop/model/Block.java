/**
 * A Block represents a contiguous sequence of characters from the Unicode
 * database. Characters in the sequence share a common property called a 
 * 'block'. A Block contains characters that are usually in the same script, 
 * but not necessarily.
 */
package com.joconner.g11n.charprop.model;

import java.util.Objects;

/**
 *
 * @author joconner
 */
public class Block {
    private String name;
    private int begin;
    private int end;

    public Block() {
    }

    
    public Block(String name, int begin, int end) {
        this.name = name;
        this.begin = begin;
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + this.begin;
        hash = 37 * hash + this.end;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Block other = (Block) obj;
        if (this.begin != other.begin) {
            return false;
        }
        if (this.end != other.end) {
            return false;
        }
        if (!this.name.equals(other.name)) {
            return false;
        }
        return true;
    }
    
    
    
}
