/*
 * Copyright (c) 2014
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice, this
 *     list of conditions and the following disclaimer.
 *  2. Redistributions in binary form must reproduce the above copyright notice,
 *     this list of conditions and the following disclaimer in the documentation
 *     and/or other materials provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 *  ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 *  ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *  (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *  LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *  SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those
 *  of the authors and should not be interpreted as representing official policies,
 *  either expressed or implied, of the FreeBSD Project.
 */

package net.jve.math;

/**
 * Created by ben on 01/05/14.
 */
public class Vector3f
{
    private float x;
    private float y;
    private float z;

    public Vector3f(float x, float y, float z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float length()
    {
        return (float)Math.sqrt(x * x + y * y + z * z);
    }

    public float max()
    {
        return Math.max(x, Math.max(y, z));
    }

    public float dot(Vector3f r)
    {
        return x * r.getX() + y * r.getY() + z * r.getZ();
    }

    public Vector3f cross(Vector3f r)
    {
        float x_ = y * r.getZ() - z * r.getY();
        float y_ = z * r.getX() - x * r.getZ();
        float z_ = x * r.getY() - y * r.getX();

        return new Vector3f(x_, y_, z_);
    }

    public Vector3f normalized()
    {
        float length = length();

        return new Vector3f(x / length, y / length, z / length);
    }

    public Vector3f rotate(Vector3f axis, float angle)
    {
        float sinAngle = (float)Math.sin(-angle);
        float cosAngle = (float)Math.cos(-angle);

        return this.cross(axis.mul(sinAngle)).add(           //Rotation on local X
                (this.mul(cosAngle)).add(                     //Rotation on local Z
                        axis.mul(this.dot(axis.mul(1 - cosAngle))))); //Rotation on local Y
    }

    public Vector3f rotate(Quaternion rotation)
    {
        Quaternion conjugate = rotation.conjugate();

        Quaternion w = rotation.mul(this).mul(conjugate);

        return new Vector3f(w.getX(), w.getY(), w.getZ());
    }

    public Vector3f lerp(Vector3f dest, float lerpFactor)
    {
        return dest.sub(this).mul(lerpFactor).add(this);
    }

    public Vector3f add(Vector3f r)
    {
        return new Vector3f(x + r.getX(), y + r.getY(), z + r.getZ());
    }

    public Vector3f add(float r)
    {
        return new Vector3f(x + r, y + r, z + r);
    }

    public Vector3f sub(Vector3f r)
    {
        return new Vector3f(x - r.getX(), y - r.getY(), z - r.getZ());
    }

    public Vector3f sub(float r)
    {
        return new Vector3f(x - r, y - r, z - r);
    }

    public Vector3f mul(Vector3f r)
    {
        return new Vector3f(x * r.getX(), y * r.getY(), z * r.getZ());
    }

    public Vector3f mul(float r)
    {
        return new Vector3f(x * r, y * r, z * r);
    }

    public Vector3f div(Vector3f r)
    {
        return new Vector3f(x / r.getX(), y / r.getY(), z / r.getZ());
    }

    public Vector3f div(float r)
    {
        return new Vector3f(x / r, y / r, z / r);
    }

    public Vector3f abs()
    {
        return new Vector3f(Math.abs(x), Math.abs(y), Math.abs(z));
    }

    public String toString()
    {
        return "(" + x + " " + y + " " + z + ")";
    }

    public Vector2f getXY() { return new Vector2f(x, y); }
    public Vector2f getYZ() { return new Vector2f(y, z); }
    public Vector2f getZX() { return new Vector2f(z, x); }

    public Vector2f getYX() { return new Vector2f(y, x); }
    public Vector2f getZY() { return new Vector2f(z, y); }
    public Vector2f getXZ() { return new Vector2f(x, z); }

    public Vector3f set(float x, float y, float z) { this.x = x; this.y = y; this.z = z; return this; }
    public Vector3f set(Vector3f r) { set(r.getX(), r.getY(), r.getZ()); return this; }

    public float getX()
    {
        return x;
    }

    public void setX(float x)
    {
        this.x = x;
    }

    public float getY()
    {
        return y;
    }

    public void setY(float y)
    {
        this.y = y;
    }

    public float getZ()
    {
        return z;
    }

    public void setZ(float z)
    {
        this.z = z;
    }

    public boolean equals(Vector3f r)
    {
        return x == r.getX() && y == r.getY() && z == r.getZ();
    }
}