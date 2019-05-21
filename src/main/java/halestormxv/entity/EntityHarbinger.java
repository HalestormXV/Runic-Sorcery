package halestormxv.entity;

import halestormxv.init.ItemInit;
import halestormxv.utility.handlers.SoundsHandler;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

public class EntityHarbinger extends EntityBlaze
{
    public EntityHarbinger(World worldIn)
    {
        super(worldIn);

        //AI Component
        this.tasks.addTask(6, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.applyEntityAI();

        this.setSize(0.6f, 1.5f);
    }

    protected void applyEntityAI()
    {
        this.tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[] {EntityPigZombie.class}));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, true));
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.23000000417232513D);
        this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
        this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(3.5D);
    }

    @Override
    protected void entityInit()
    {
        super.entityInit();
    }

    @Override
    protected SoundEvent getAmbientSound()
    {
        return SoundsHandler.ENTITY_PHANTOM_AMBIENT;
    }

    @Override
    protected SoundEvent getDeathSound()
    {
        return SoundsHandler.ENTITY_PHANTOM_DEATH;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source)
    {
        return SoundsHandler.ENTITY_PHANTOM_HURT;
    }

    @Override
    public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.UNDEAD;
    }

    @Override
    public void onLivingUpdate()
    {
        super.onLivingUpdate();
    }

    @Override
    public void onDeath(DamageSource cause)
    {
        super.onDeath(cause);
    }

    @Override
    protected Item getDropItem()
    {
        return ItemInit.DUST_MYSTERIUM;
    }

    @Override
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata)
    {
        livingdata = super.onInitialSpawn(difficulty, livingdata);
        return livingdata;
    }

    @Override
    public float getEyeHeight()
    {
        float f = 1.34F;
        return f;
    }
}
