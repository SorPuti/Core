package br.com.puti.core.component.npc.api.virtual.holograms;

import net.minecraft.server.v1_8_R3.AxisAlignedBB;
import net.minecraft.server.v1_8_R3.MovingObjectPosition;
import net.minecraft.server.v1_8_R3.Vec3D;

public class NullBoundingBox extends AxisAlignedBB {

  public NullBoundingBox() {
    super(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
  }

  @Override
  public double a() {
    return 0.0;
  }

  @Override
  public AxisAlignedBB a(AxisAlignedBB arg0) {
    return this;
  }

  @Override
  public double a(AxisAlignedBB arg0, double arg1) {
    return 0.0;
  }

  @Override
  public AxisAlignedBB a(double arg0, double arg1, double arg2) {
    return this;
  }

  @Override
  public boolean a(Vec3D arg0) {
    return false;
  }

  @Override
  public MovingObjectPosition a(Vec3D arg0, Vec3D arg1) {
    return super.a(arg0, arg1);
  }

  public boolean b(AxisAlignedBB arg0) {
    return false;
  }

  @Override
  public double b(AxisAlignedBB arg0, double arg1) {
    return 0.0;
  }
  
  @Override
  public double c(AxisAlignedBB arg0, double arg1) {
    return 0.0;
  }
  
  @Override
  public AxisAlignedBB c(double arg0, double arg1, double arg2) {
    return this;
  }
  
  @Override
  public AxisAlignedBB grow(double arg0, double arg1, double arg2) {
    return this;
  }
  
  @Override
  public AxisAlignedBB shrink(double arg0, double arg1, double arg2) {
    return this;
  }
}
